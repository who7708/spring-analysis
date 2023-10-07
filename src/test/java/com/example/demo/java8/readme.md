# java8 新特性
## Lambda 和函数式接口
```
@FunctionalInterface
interface Operation {
    int operation(int a, int b);
}
```

## 方法引用
```

```

## 接口默认方法和静态方法

## 重复注解

## 类型注解

## 更好的类型推断
```
java7：
List<String> stringList = new ArrayList<>();
stringList.add("A");
stringList.addAll(Arrays.<String>asList());

java8：
List<String> stringList = new ArrayList<>();
stringList.add("A");
stringList.addAll(Arrays.asList());
```

## Optional

## Stream

## 日期时间 API
```
LocalDate now = LocalDate.now();
System.out.println(now);
System.out.println(now.getYear());
System.out.println(now.getMonth());
System.out.println(now.getDayOfMonth());

LocalTime localTime = LocalTime.now();
System.out.println(localTime);
LocalDateTime localDateTime = now.atTime(localTime);
System.out.println(localDateTime);
```

## Base64 支持
```
String base64 = Base64.getEncoder().encodeToString("aaa".getBytes());
System.out.println(base64);
byte[] bytes = Base64.getDecoder().decode(base64);
System.out.println(new String(bytes));
```

## 并行数组 ParallelSort
```
Arrays.parallelSort(new int[] {1, 2, 3, 4, 5});
```