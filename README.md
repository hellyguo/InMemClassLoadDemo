# Demo: Loading classes in memory

[中文版](README_cn.md)

## jdk8

### use `ClassLoader#defineClass`

1. define a class loader, extends `URLClassLoader`
2. define a new public method, call `defineClass` indirectly

```java
    public Class<?> loadClassFromBytes(String className, byte[] bytes) {
        return this.defineClass(className, bytes, 0, bytes.length);
    }
```

### use `Unsafe#defineAnonymousClass`

> need to use `sun.misc.Unsafe`, which is not recommended

```java
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(SimTarget.class, classBytes, EMPTY_OBJECTS);
    }
```

## jdk11

### use `ClassLoader#defineClass`

same as `jdk8`

### use `Unsafe#defineAnonymousClass`

> need to use jvm argument: `--add-exports java.base/jdk.internal.misc=<module>`

```java
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(AnonClassHolder.class, classBytes, EMPTY_OBJECTS);
    }
```

## jdk17

### use `ClassLoader#defineClass`

same as `jdk8`

### use `MethodLookup#defineHiddenClass`

```java
    public static Class<?> loadClass(String className, byte[] classBytes) {
        if (!className.startsWith(PACKAGE_NAME)) {
            throw new RuntimeException("not same package, should failed");
        }
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            return lookup.defineHiddenClass(classBytes, true,
                                            MethodHandles.Lookup.ClassOption.NESTMATE,
                                            MethodHandles.Lookup.ClassOption.STRONG)
                         .lookupClass();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
```

## HowTo

1. (`optional`) cd `demo-inmem-base`
2. (`optional`) compile
    
    ```shell
    mvn package -Dmaven.test.skip=true
    # or
    mvnd package -Dmaven.test.skip=true
    ```
    
3. (`optional`) call `class2bytes.sh`, generate the Java file, which is hold bytes.
4. test in `demo-inmem-jdk8`/`demo-inmem-jdk11`/`demo-inmem-jdk17`
