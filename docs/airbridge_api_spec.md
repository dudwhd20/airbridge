# ✈️ AirBridge API 명세서 (2025)

MSA 구조 기반 항공 예약 시스템의 각 도메인별 REST API 목록입니다.

---

## 🧑‍톼 User Service

### 🔹 POST /api/users/register
- 회원가입
- Body:
```json
{
  "email": "user@example.com",
  "password": "secure123",
  "name": "홍길동"
}
```

---

### 🔹 POST /api/users/login
- 로그인 및 JWT 발급
- Body:
```json
{
  "email": "user@example.com",
  "password": "secure123"
}
```

- Response:
```json
{
  "token": "jwt-token-here"
}
```

---

### 🔹 GET /api/users/me
- 내 정보 조회 (JWT 필요)

---

### 🔹 PUT /api/users/me
- 내 정보 수정
- Body:
```json
{
  "name": "변객된 이름"
}
```

---

## 🚲 Reservation Service

### 🔹 GET /api/flights
- 항공편 목록 조회
- Query Params: `from`, `to`, `date`

---

### 🔹 GET /api/flights/{id}
- 특정 항공편 상세 조회

---

### 🔹 POST /api/reservations
- 예약 요청 (자리 선택 포함)
- Body:
```json
{
  "flightId": "F001",
  "seat": "12A"
}
```

---

### 🔹 GET /api/reservations/{id}
- 예약 상세 조회

---

### 🔹 PUT /api/reservations/{id}/cancel
- 예약 취소

---

## 💳 Payment Service

### 🔹 POST /api/payments
- 결제 요청
- Body:
```json
{
  "reservationId": "R123456",
  "paymentMethod": "CARD"
}
```

---

### 🔹 GET /api/payments/{id}
- 결제 상세 조회

---

### 🔹 GET /api/payments/reservation/{reservationId}
- 특정 예약에 대한 결제 내역 조회

---

## 🎫 Ticketing Service

### 🔹 GET /api/tickets/{id}
- 티켓 상세 조회

---

### 🔹 GET /api/tickets/reservation/{reservationId}
- 예약 기준 티켓 조회

