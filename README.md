Example of how __Hibernate Validator 4__ works with method validation when:

* one of params is list
* method is enhanced

Just perform tests. Tests runs validation of method 100 times. And some of validations pass.

```
Failed tests:
    testValidateMethodWithListOfEntities(com.omaha.hibernate.ValidationExampleTest): validated mismatch expected:<0> but was:<42>
    testValidateMethodWithListOfPrimitives(com.omaha.hibernate.ValidationExampleTest): validated mismatch expected:<0> but was:<53>
```

However, __Hibernate Validator 5__ works with it correctly.