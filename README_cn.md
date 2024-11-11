# 样例: 从内存中加载 Class

[英文版](README.md)

## jdk8

### 使用 `ClassLoader#defineClass`

1. 定义一个类加载器，继承 `URLClassLoader`
2. 定义一个新方法，间接调用 `defineClass`

```java
    public Class<?> loadClassFromBytes(String className, byte[] bytes) {
        return this.defineClass(className, bytes, 0, bytes.length);
    }
```

### 使用 `Unsafe#defineAnonymousClass`

> 需要用不被建议使用的 `sun.misc.Unsafe`

```java
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(SimTarget.class, classBytes, EMPTY_OBJECTS);
    }
```

## jdk11

### 使用 `ClassLoader#defineClass`

同 `jdk8`

### 使用 `Unsafe#defineAnonymousClass`

> 需要添加 `JVM` 参数: `--add-exports java.base/jdk.internal.misc=<module>`

```java
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(AnonClassHolder.class, classBytes, EMPTY_OBJECTS);
    }
```

## jdk17

### 使用 `ClassLoader#defineClass`

同 `jdk8`

### 使用 `MethodLookup#defineHiddenClass`

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

## 使用方法

1. 切换到 `demo-inmem-base`
2. 编译

    ```shell
    mvn package -Dmaven.test.skip=true
    # or
    mvnd package -Dmaven.test.skip=true
    ```

3. 执行 `class2bytes.sh`, 生成持有 `class bytes` 的 Java 文件.
4. 在 `demo-inmem-jdk8`/`demo-inmem-jdk11`/`demo-inmem-jdk17` 中测试
