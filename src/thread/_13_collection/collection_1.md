## 동시성 컬렉션이 필요한 이유
`java.util` 패키지에 소속되어 있는 컬렉션 프레임워크는 원자적인 연산을 제공할까?

**예를 들어**
```java
public class SimpleListMainV0 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        
        // 스레드1, 스레드2가 동시에 실행 가정
        list.add("A"); // 스레드1 실행 가정
        list.add("B"); // 스레드2 실행 가정
        System.out.println(list);
    }
}
```
- 스레드1: `list`에 `A`를 추가한다.
- 스레드2: `list`에 `B`를 추가한다.

데이터를 추가하는 `add()` 메서드를 보면 단순히 컬렉션에 데이터를 하나 추가하는 원자적인 연산처럼 보인다.
하지만 **컬렉션 프레임워크가 제공하는 대부분의 연산은 원자적인 연산이 아니다.**
---

### 컬렉션을 직접 만들어 테스트 해보자.

`BasicList`
- 가장 간단한 컬렉션의 구현. 내부에서는 배열을 사용해서 데이터를 보관한다.
- `ArrayList` 의 최소 구현 버전
- `DEFAULT_CAPACITY`: 최대 5의 데이터를 저장할 수 있다.
- `size`: 저장한 데이터의 크기
---

 `BasicList_Main_1` 실행

**실행 결과**
```
# 아무런 문제 없음
list = [A, B] size=2, capacity=5
```

---
## 동시성 컬렉션이 필요한 이유2

**add() - 원자적이지 않은 연산**
```java
    @Override
    public void add(Object e) {
        elementData[size] = e;
        // 멀티스레드 문제를 쉽게 확인하기 위한 코드
        sleep(100);
        size++;
    }
```
이 메서드는 단순히 데이터를 추가하는 것으로 끝나지 않고, 내부에 있는 **배열 데이터를 추가**해야하고,
`size`도 함께 하나 증가시켜야 한다. `size++` 연산도 원자적이지 않다.
이런 원자적이지 않은 연산을 멀티스레드 상황에서 사용하려면 우리가 지금까지 학습했던
`synchronized`, `Lock` 등을 사용해야 된다.


### 문제 발생
`BasicList_Main_2` 실행

**실행 결과**
```
09:21:32.763 [     main] BasicList
09:21:32.868 [ Thread-0] Thread-1: list.add(A)
09:21:32.869 [ Thread-1] Thread-2: list.add(B)
09:21:32.869 [     main] [B, null] size=2, capacity=5
```

**실행 결과 `size` 는 2가 나오는데 데이터는 하나만 입력되었다.**
```java
    @Override
    public void add(Object e) {
        elementData[size] = e; // "Thread-1", "Thread-2" 이 동시에 수행됨
        sleep(100);
        size++;
    }
```

**과정**
1. **스레드1**: `elementData[0] = A`, `elementData[0]` 의 값은 `A`
2. **스레드2**: `elementData[0] = B`, `elementData[0]` 의 값은 `B`

---

### 정리

**컬렉션 프레임워크 대부분은 스레드 세이프 하지 않다.**
`ArrayList`, `LinkedList`, `HashSet`, `HashMap` 등 수 많은 자료 구조들은 
단순한 연산을 제공하는 것 처럼 보인다. 하지만 그 내부에서는 수 많은 연산들이 사용된다.
배열에 데이터를 추가하고, 사이즈를 변경하고, 배열을 새로 만들어서 배열의 크기도 늘리고,
노드를 만들어서 링크에 연결하는 등 수 많은 복잡한 연산이 사용된다.

따라서 일반적인 컬렉션들은 절대로 스레드 세이프 하지 않다.
**단일 스레드** 가 아니라 **멀티 스레드** 가 동시에 접근하는 경우라면 `java.util` 패키지가
제공하는 일반적인 컬렉션들은 사용하면 안된다.

