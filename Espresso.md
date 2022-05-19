### Espresso

Espresso是一个Google官方提供的Android应用UI自动化测试框架。

先启用开发者选项，再在开发者选项下，停用以下三项设置：

- 窗口动画缩放
- 过渡动画缩放
- Animator 时长缩放

#### 配置

在app的build.gradle下添加依赖

```groovy
androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
androidTestImplementation 'androidx.test:runner:1.4.0'
androidTestImplementation 'androidx.test:rules:1.4.0'
```

设置插桩测试运行程序，在同一个build.gradle文件的android.defaultConfig中添加

```groovy
testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
```

#### Api组件

常用的Api组件包括：

- Espresso  用于与视图交互（通过 onView() 和 onData()）的入口点。此外，还公开不一定与任何视图相关联的 API，如 pressBack()。
- ViewMatchers  实现 Matcher<? super View> 接口的对象的集合。您可以将其中一个或多个对象传递给 onView() 方法，以在当前视图层次结构中找到某个视图。
- ViewAction 可传递给 ViewInteraction.perform() 方法的 ViewAction 对象（如 click()）的集合。
- ViewAssertions 可传递给 ViewInteraction.check() 方法的 ViewAssertion 对象的集合。在大多数情况下，您将使用 matches 断言，它使用视图匹配器断言当前选定视图的状态。

![截屏2022-05-18 下午12.02.50](/Users/xieying/Desktop/截屏2022-05-18 下午12.02.50.png)

https://android.github.io/android-test/downloads/espresso-cheat-sheet-2.1.0.pdf

#### 使用

```java
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test() {
        onView(withId(R.id.tv_test01)).perform(click());
        onView(withId(R.id.tv_test01)).check(matches(withText("测试1")));
    }
}
```

- 在测试用例类的类添加@RunWith的注解，并且指定测试平台为AnroidJUnit4
- 如果允许测试需要较大消耗，可以使用@LargeTest注解
- 设置ActivityTestRule用来指明被测试的Activity，使用@Rule注解
- 测试方法使用@Test注解

##### ViewMachers查找View

在OnView方法里面，我们是通过ViewMachers查找到对应的View，可以通过withId(),withText()....等等方法去匹配到对应的View，只要确保你所找的控件在页面上确实存在就好

我们也可以通过allOf,anyOf这种方式指定满足多个条件或者满足任意的条件

##### ViewActions执行事件

对View的操作：onView(...).perform(...)，可以在perform(...)执行多个操作，比如onView(...).perform(click(),clearText());

**所有的操作都有一个前提：执行的View必须要在当前页面显示出来**

| 方法名              | 含义           |
| :------------------ | :------------- |
| click()             | 点击view       |
| clearText()         | 清除文本内容   |
| swipeLeft()         | 从右往左滑     |
| swipeRight()        | 从左往右滑     |
| swipeDown()         | 从上往下滑     |
| swipeUp()           | 从下往上滑     |
| click()             | 点击view       |
| closeSoftKeyboard() | 关闭软键盘     |
| pressBack()         | 按下物理返回键 |
| doubleClick()       | 双击           |
| longClick()         | 长按           |
| scrollTo()          | 滚动           |
| replaceText()       | 替换文本       |
| openLinkWithText()  | 打开指定超链   |

##### ViewAssertion 断言应用

check(......)，最常用的断言是matches()，它断言当前选定视图的状态，上面的实例就是断言id为tv_test01的View它是否和text为"改变成功的View匹配"

##### AdapterView

`AdapterView` 是一种特殊类型的微件，可从适配器动态加载其数据。最常见的 `AdapterView` 示例是 `ListView`。与 `LinearLayout` 之类的静态微件相反，只能将一部分 `AdapterView` 子视图加载到当前视图层次结构中。简单的 `onView()` 搜索将找不到当前未加载的视图。

Espresso 处理此问题的方法是提供一个单独的 `onData()` 入口点，该入口点能够先加载相关适配器项目，并在对其或其任何子级执行操作之前使其处于聚焦状态。

##### RecyclerView

`RecyclerView` 对象的工作方式与 `AdapterView` 对象不同，因此不能使用 `onData()` 方法与其交互。

要使用 Espresso 与 RecyclerView 交互，您可以使用 `espresso-contrib` 软件包，该软件包具有 [`RecyclerViewActions`](https://developer.android.com/reference/androidx/test/espresso/contrib/RecyclerViewActions?hl=zh-cn) 的集合，可用于滚动到相应位置或对项目执行操作：

- `scrollTo()` - 滚动到匹配的视图。
- `scrollToHolder()` - 滚动到匹配的数据视图持有者。
- `scrollToPosition()` - 滚动到特定位置。
- `actionOnHolderItem()` - 对匹配的视图持有者执行视图操作。
- `actionOnItem()` - 对匹配的视图执行视图操作。
- `actionOnItemAtPosition()` - 在特定位置对视图执行视图操作。





