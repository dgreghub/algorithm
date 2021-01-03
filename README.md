![header](https://capsule-render.vercel.app/api?type=wave&color=timeGradient&height=300&section=header&text=OneDayOneCommit&fontSize=45)	

# Software Basic algorithm<p>
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/> 
</p>
기본적인 자료구조와 시간복잡도 알고리즘 해설을 다루도록 하겠다.

# How to solve the problem
<h3>1. 최상단 시간 제한에 주목하라!</h3>
50 개의 Test Input 입력시 C/C++ 1.5초 / Java 2초 입니다.
한 개의 테스트 케이스 당 40ms로 나와야 합니다. (400만 / 1억 약 1초)

** 400만의 크기에 적합한 자료 구조 및 탐색 법
N이 2,000 인 경우 완전 탐색  N*N = 2,000 * 2,000 = 400만
N이 200,000인 경우, 부분배열 K크기인 N-K+1개의 특정값 i를  이진 탐색으로 찾는 경우 (N-K+1) * log2N  = 200,000 * 18 = 360만 
위와 같이, 탐색할 수 있는 자료의 범위가 곧 시간 복잡도를 뜻합니다. 

<h3>2. 최상단 메모리 제한도 체크한다.</h3>
메모리도 힌트가 됩니다. 먼저 우리가 자주 사용하는 자료형을 예로 들어보겠습니다
자료형 int는 4byte, long은 16byte 입니다. 2개의 자료형은 알고리즘 대회에서 가장 빈번히 사용됩니다.
4000 * 4000 배열을 만든다고 해봅시다. 1천 600만 크기의 배열이 만들어 집니다.

    int 인 경우 , 4byte * 16,000,000 = 64MB
    long 인 경우, 16Byte * 16,000,000 = 256MB

우리가 만약 과도하게 long으로 배열을 만들게 되면, 더 이상의 배열을 만들 수 없게 됩니다.
그렇기 때문에, int가 누적 및 저장 할 수 있는 범위에 따라 알맞게 long으로 변형하여 쓰는 연습이 필요합니다.


<h3>3. 문제의 지문을 정확히 이해한다.</h3>
문제가 주어지면, 주어진 예제를 바탕으로 충분히 이해한다.
그 이후, 단서들을 정리한다.

이때, 문제를 많이 풀어본 경험을 활용하여, 익숙한 용어로 재정의한다.
Node(도시, 섬, 가게, 지점) : 탐색하거나, 계산을 진행해야 되는 자료구조 일 경우가 크다.
간선(시간, 비용) : 자료구조 탐색 시, 더해지거나 빼지는 값일 가능성이 있다. 때론 이 간선만 가지고 검색을 해야 하는 경우도 있다.

<h3>정렬/치환 후 탐색 하라!</h3>
Input 값이 주어지면, 그 값을 있는 그대로 탐색하는 경우가 많다. 하지만 그 값은 최적화된 값이 아니다.

탐색에 앞서 먼저 다음과 같은 과정을 거친다면 범위가 줄어드는지 검토하라.

       1. 정렬을 실시한 뒤 탐색해본다.
       2. 정렬 수행 된 값을 특정 값으로 치환하여 탐색해본다.
       3. 탐색 방법을 이분 탐색 방법으로 수행한다.

<h4>여기서 잠깐! log2N 의 복잡도가 소요되는 이분 탐색 종류</h4>

       1. PQ - Queue에 들어가 있는 값 중, 최대나 최소의 값을 우선적으로 탐색하여 사용가능
       2. 이분탐색, 파라메트릭서치 - Mid 값을 기준으로 크고 작음을 판단, 원하는 값을 찾음
       3. 인덱스 트리용 이분탐색 - 구간합의 성질을 이용하여, leaf 노드의 위치로 원하는 값을 찾음
       4. LCA 용 이분탐색 - 최대 N 만큼 떨어져 있는 부모를 log2N depth를 기준으로 2^k~2^0까지 순차적으로 값을 찾음

# 자료구조

# 시간복잡도 해석

# 알고리즘

![footer](https://capsule-render.vercel.app/api?type=wave&color=timeGradient&height=200&section=footer&fontSize=90)
