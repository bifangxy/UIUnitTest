### Mock & Mockito

单元测试的思路就是我们想在不涉及依赖关系的情况下测试代码。这种测试可以让你无视代码的依赖关系去测试代码的有效性。核心思想就是如果代码按设计正常工作，并且依赖关系也正常，那么他们应该会同时工作正常。

有些时候，我们代码所需要的依赖可能尚未开发完成，甚至还不存在，那如何让我们的开发进行下去呢？使用mock可以让开发进行下去，**mock技术的目的和作用就是模拟一些在应用中不容易构造或者比较复杂的对象，从而把测试与测试边界以外的对象隔离开**。

**Mockito就是一个优秀的用于单元测试的mock框架**

##### 简单使用场景

见代码.....

##### 创建mock对象

- 使用静态mock方法

- 使用@Mock注解，使用注解需要初始化，有以下三种方式初始化：

  - MockitoAnnotations.openMocks(this);

  - @RunWith(MockitoJUnitRunner.class)进行初始化

  - @Rule public MockitoRule mMockitoRule = MockitoJUnit.rule();

##### 常用使用方式

- 验证行为
  常用的几个方法：

  - Mockito.times()
  - Mockito.atLeast()
  - Mockito.atMost()
  - Mockito.never()
  - ........
  
- 测试桩 Stub

  对于stubbing，有以下几点需要注意

  - 对于有返回值的方法，mock会返回null、空集合、默认值。比如为String返回null，boolean/Boolean返回false
  - stubbing可以被覆盖
  - 一旦stubbed，不管调用多少次，方法都会返回stubbed的值
  - 当你对同一个方法进行多次stubbing，最后一次stubbing是最重要的

- 参数匹配

  如果使用了参数匹配器，那么所有的参数都应该使用参数匹配器

- 为void方法抛异常

- 验证调用顺序

- 验证mock对象没有产生过交互

- 查找是否有未验证的交互

- 根据调用顺序设置不同的stubbing

- doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod()等用法

- spy监视真正对象

- 为未stub的方法设置默认返回值

- 参数捕捉

- 超时验证












https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#stubbing











