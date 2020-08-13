# kotlin-simpay-api

## SMS
### Weryfikacja kodu
```kotlin
val sms: Sms =  Sms()

val request: CodeVerifyRequest = CodeVerifyRequest()
val request: CodeVerifyRequest = CodeVerifyRequest(API_KEY, API_SECRET, SERVICE_ID)
request.number = "number"
request.code = "code"

val response: ApiResponse<CodeVerifyResponse> = sms.verifyCode(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val codeVerifyResponse: CodeVerifyResponse = response.respond
```

### Pobieranie listy usług
```kotlin
val sms: Sms =  Sms()

val request: ServiceListRequest = ServiceListRequest("API_KEY", "API_SECRET")

request.key = "key" // can be omitted  by passing value in constructor
request.secret = "API_SECRET" // ca
// n be omitted  by passing value in constructor
val response: ApiResponse<ServicesResponse> = sms.getServiceList(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val serviceList: ServicesResponse = response.respond
```

## SMS XML
```kotlin
val smsXml: SmsXml = SmsXml("apikey")

val code: String = smsXml.generateCode() // Generate code
val number: Int = smsXml.getSmsValue("number") // retrieve information"s about sms
val sms: String = smsXml.generateXml("sms") // Generate xml from sms message
val ip: Boolean = smsXml.getServersIp("ip") // Check if passed ip is valid ip of simpay servers
```

## Direct Billing
### Generowanie transakcji
```kotlin
val directBilling: DirectBilling = DirectBilling()

val request: DbGenerateRequest = DbGenerateRequest("SERVICE_ID")

request.amount = "amount" 
request.control = "control" 

val dbGenerateResponse: DbGenerateResponse = directBilling.generateTransaction(API_KEY, request)
```

### Pobieranie danych o transakcji
```kotlin
val directBilling: DirectBilling = DirectBilling()

val request: DbTransactionRequest = DbTransactionRequest()
request.id = 1 // can be omitted  by passing value in constructor
request.key = "key" // can be omitted  by passing value in constructor
request.secret = "API_SECRET"  // can be omitted  by passing value in constructor

val response: ApiResponse<DbTransaction> = directBilling.getTransaction(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val respond: DbTransaction = response.respond // can be omitted  by passing value in constructor
```

### Pobieranie listy usług DCB
```kotlin
val directBilling: DirectBilling = DirectBilling()

val request: DbServicesListRequest = DbServicesListRequest()
val request: DbServicesListRequest = DbServicesListRequest(API_KEY,API_SECRET)
request.key = "API_KEY" // can be omitted  by passing value in constructor
request.secret = "API_SECRET"  // can be omitted  by passing value in constructor

val response: ApiResponse<DbServicesListResponse> = directBilling.getServices(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val respond: DbServicesListResponse = response.respond
```

### Pobieranie maksymalnych kwot transakcji
```kotlin
val directBilling: DirectBilling = DirectBilling()

val request: DbTransactionLimitsRequest = DbTransactionLimitsRequest("SERVICE_ID")

request.key = "API_KEY"
request.secret = "API_SECRET"

val response: ApiResponse<List<DbTransactionLimit>> = directBilling.getTransactionLimits(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val respond: List<DbTransactionLimit> = response.respond
```

### Pobieranie prowizji dla usługi
```kotlin
val directBilling: DirectBilling = DirectBilling()

val request: DbServiceCommissionRequest = DbServiceCommissionRequest()
val request: DbServiceCommissionRequest = DbServiceCommissionRequest("SERVICE_ID")


request.key = "API_KEY" 
request.secret = "API_SECRET"

val response: ApiResponse<List<DbCommission>> = directBilling.getServiceCommission(request)
val error: Array<String> = response.error // List of errors, if request was successful list will be empty
val respond: List<DbCommission> = response.respond
```

### Pobieranie adresów IP serwerów SimPay
```kotlin
val directBilling: DirectBilling = DirectBilling()

val response: List<String> = directBilling.getServersIp()
```

### Obliczanie podpisu sign
```kotlin
val directBilling: DirectBilling = DirectBilling()

val sign: String = directBilling.sign(123, "status", "valuenet", "value_partner", "control")
```
