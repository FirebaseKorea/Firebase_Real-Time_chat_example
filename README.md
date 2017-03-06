# Firebase_Real-Time_chat_example

Firebase의 Real-Time Database를 이용한 Android 실시간 채팅 예제입니다.

## Firebase Version

```{java}
com.google.firebase:firebase-core:10.0.1
com.google.firebase:firebase-database:10.0.1
```

## Firebase Database Rules

```{json}
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```
```read```와 ```write``` 권한을 true로 돌리면 누구나 DB에 접근할 수 있습니다.

예제를 실습하신 이후엔 꼭 원상복귀하시기 바랍니다.
