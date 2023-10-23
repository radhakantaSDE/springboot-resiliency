# springboot-resiliency

Resiliency is a safeguard for a service from failure / overload

We can achieve service resiliency (RateLimiter, Retry, CircuitBreaker, BulkHead) in 2 ways
1. Adding properties in application.yml
2. Dynamically adding of resiliency

### **Description**

**RateLimiter** : It restricts number of call allowed for a api/method/instance

**Retry** : It will retry (specific number of times) the call if it will fail

**CircuitBreaker** : If a method is failing again and again then, it will not call the method

**BulkHead** : Used to limit the number of concurrent execution of a api/method