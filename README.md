# routingDB

- 1개의 Thread는 여러개의 DB를 연결해 둘 수 있다.
- 그 중 하나의 DB를 선택하여 사용할 수 있다.
- 단, 조회만 할 수 있다.
- 연결 종료를 할 경우 스레드를 닫는다.
