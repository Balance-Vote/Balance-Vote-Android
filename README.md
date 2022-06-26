
![playstore](https://user-images.githubusercontent.com/37128456/173813087-615167a3-a8ca-461d-921a-c6900d396199.png)

# BalanceVote

![GitHub release (latest by date)](https://img.shields.io/github/v/release/Balance-Vote/Balance-Vote-Android)
![GitHub last commit](https://img.shields.io/github/last-commit/Balance-Vote/Balance-Vote-Android)

<a href='https://play.google.com/store/apps/details?id=com.teamnoyes.balancevote'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="70" width="180"/></a>
<br>

BalanceVote는 다른 사용자가 제시한 두 가지 제시어중 하나를 선택하고 사용자들간 댓글을 통한 의견 교환을 할 수 있는 애플리케이션입니다.

# 기능 소개

## 닉네임 입력

<img src="https://user-images.githubusercontent.com/37128456/173822219-ee402514-18a9-4176-a22a-574443ba7d68.png" width="400" />

앱 실행시마다 닉네임을 입력하도록 합니다. 일체의 개인정보를 서버에 저장하지 않기 위해 사용자를 특정할 수 있는 UUID 등을 수집하지 않습니다. 닉네임으로 개인을 특정하는 것을 방지하기 위해 닉네임은 중복될 수 있으며, 서버에서 닉네임의 유효성이나 중복을 검사하지 않습니다.

## 투표 리스트

<img src="https://user-images.githubusercontent.com/37128456/173842903-3b1b3e36-accf-46ee-b0a9-71d84bb70665.png" width="400" />

닉네임을 입력한 뒤 나타나는 화면입니다. 상단에는 가장 많은 투표 수, 가장 많은 댓글 수를 기록한 투표를 표시하여 많은 사람들이 관심을 가지고 있는 투표가 어떤 것인지 알 수 있습니다.
하단에는 최신순으로 투표 목록들이 표시됩니다.


## 투표 선택
<img src="https://user-images.githubusercontent.com/37128456/173842967-c728e490-b853-4f6c-ab12-de1c56accb6a.png" width="400" />

투표를 선택하면 두 가지 중 하나를 고를 수 있는 화면으로 이동합니다. 둘 중 하나를 선택합니다.


## 투표 결과
<img src="https://user-images.githubusercontent.com/37128456/173842985-ccc6caae-4c6d-428f-8b55-00427bdb3f41.png" width="400" />

사용자의 선택이 반영된 현재까지의 투표 결과를 표시합니다.

# 사용 기술

## Jetpack Compose

애플리케이션의 UI 레이어는 모두 Jetpack Compose를 사용하여 구현하였습니다. UI 레이어의 

### 선언적 그래픽

위에서 그려진 그래프는 Compose의 `Canvas` 객체를 사용하여 구현하였습니다.

Compose의 `Canvas` 객체는 기존의 `Canvas`와 완전히 다른 새로운 구현이 아닌, 생성하면 이 객체가 View System의 기존 `Canvas`를 생성하고 관리하는 방식입니다. 그렇지만 Compose의 장점인 업데이트시 재구성, 상태 관리 등의 장점을 그대로 사용할 수 있고, 기존의 `Canvas`에 비해 더 직관적으로 코드를 관리할 수 있습니다.

### Navigation

Navigation 또한 기존의 Fragment 방식 대신 Compose에서 `Composable` 간의 이동으로 구현하였습니다.

View System 에서 Navigation 그래프를 XML 파일로 생성하고 시각적으로 볼 수 있었던 것에 비해, Navigation Compose `NavHost`에서 직접 생성하는 방식으로 낯설어 보이지만 기존의 XML와 유사한 구조입니다. 주요한 변경점은
 - Fragment 간의 이동이 아닌 Composable 함수 간의 이동
 - Navigation 그래프의 시각적 표현은 아직 지원되지 않음

**View System**
([Android 가이드](https://developer.android.com/guide/navigation/navigation-getting-started?hl=ko)의 예시)
```XML
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android"
    app:startDestination="@id/blankFragment">
    <fragment
        android:id="@+id/blankFragment"
        android:name="com.example.cashdog.cashdog.BlankFragment"
        android:label="@string/label_blank"
        tools:layout="@layout/fragment_blank" >
        <action
            android:id="@+id/action_blankFragment_to_blankFragment2"
            app:destination="@id/blankFragment2" />
    </fragment>
    <fragment
        android:id="@+id/blankFragment2"
        android:name="com.example.cashdog.cashdog.BlankFragment2"
        android:label="@string/label_blank_2"
        tools:layout="@layout/fragment_blank_fragment2" />
</navigation>
```

**Compose**

```kotlin
NavHost(
    navController = appState.navController,
    startDestination = "entry",
    modifier = Modifier.padding(pv)
) {
    composable("splash") {
        SplashScreen(navController = appState.navController)
    }
    composable("entry") {
        EntryScreen(navController = appState.navController) {
            appState.nickname.value = it
        }
    }
    addMainGraph(appState.navController, appState.nickname) { msg ->
        scope.launch {
            scaffoldState.snackbarHostState
                .showSnackbar(msg)
        }
    }
}
```
Compose에서도 Navigation 그래프는 모든 하위 구성요소(`Composable`)에서 접근할 수 있는 곳에 위치해야 합니다. `BVApp.kt` 와 `BVAppState.kt`는 각각 앱 전체의 구조와 상태를 담당하도록 설계하였고, Navigation 그래프와 현재 위치 정보를 가집니다.

각 대상 간(`Composable`)에 데이터 전달 또한 Navigation을 사용하였습니다. 기존의 View System에서 데이터 전달을 위한 인수를 선언할 때 XML에서 Fragment 태그 내에 `argument` 태그로 선언하는 트리형 구조였던 것에 비해 Compose의 Navigation은 경로에 인수의 자리 표시자를 포함하는 방식입니다.


**View System**
([Android 가이드](https://developer.android.com/guide/navigation/navigation-pass-data?hl=ko)의 예시)
```XML
<fragment android:id="@+id/myFragment" >
    <argument
        android:name="myArg"
        app:argType="integer"
        android:defaultValue="0" />
</fragment>
```

**Compose**

```kotlin
composable("main/vote/{postId}/{left}/{right}",
    arguments = listOf(navArgument("postId") { type = NavType.StringType },
        navArgument("left") { type = NavType.StringType },
        navArgument("right") { type = NavType.StringType })) {
    val voteViewModel = hiltViewModel<VoteViewModel>()
    VoteScreen(viewModel = voteViewModel,
        postId = it.arguments?.getString("postId") ?: "",
        leftTopic = it.arguments?.getString("left") ?: "",
        rightTopic = it.arguments?.getString("right") ?: "",
        navController = navController,
        snackbarEvent = snackbarEvent,
        nickname = nickname)
}
```

중첩 그래프 생성은 View System에서와 유사하게 navigation 내에 navigation을 넣는 것으로 할 수 있습니다. 이 프로젝트에서는 전체 화면 구조 내에 하단 탐색 창을 통해 이동하는 구조를 내부의 중첩 그래프로 구성하였습니다.

## Hilt

종속성 주입을 위해 Hilt 라이브러리를 사용하였습니다. Compose에서 원활히 사용할 수 있도록 지원되기 때문에 종속성 주입에 고려되는 또다른 라이브러리인 Koin을 대신하여 선택하였습니다. 특히 이 프로젝트에 적용된 Navigation Compose를 사용한다면 이에 대한 추가적인 기능을 지원하는 것도 선택한 이유 중 하나입니다.

Navigation Compose에서 `NavHost` 내에 선언한 각 `composable`에서 탐색 대상의 인수로 들어가는 ViewModel을 `hiltViewModel()`로 인스턴스를 생성, 주입하는 방식입니다.

```kotlin
composable(BottomNavScreen.HOME.route) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    HomeScreen(homeViewModel, navController)
}
```

## RxJava3

이번 프로젝트에서는 Coroutine 대신 RxJava3와 RxAndroid를 사용하였습니다. Coroutine(+ Flow)에 비해 장점이 있어 쓰기 보다는, RxJava에 대한 사용 경험을 쌓고 싶어 선택하게 되었습니다.

프로젝트 내에서 데이터 스트림을 사용하여 처리한 예로 페이징이 있습니다. Jetpack의 Paging 3 라이브러리를 사용하여 구현하였습니다. `HomeScreen`에서 투표 리스트를 표시할 때 사용하였습니다.

다만 Compose의 Paging 3 라이브러리에 쓰이는 데이터 스트림은 Coroutine Flow를 지원하지만, RxJava는 지원하지 않습니다.

RxJava로 데이터 스트림을 전달한다면, `cachedIn()` 함수를 사용하여 `PagingData`를 캐싱하는 과정까지는 Coroutine Flow와 RxJava 모두 지원하지만(RxJava 사용시에는 `androidx.paging.rxjava2` 또는 `androidx.paging.rxjava3` 종속성 추가 필요) 이후 뷰에 표시하기 위한 `collectAsLazyPagingItems()`를 지원하지 않기 때문에 RxJava의 Flowable 데이터 스트림을 Coroutine Flow의 데이터 스트림으로 바꾼 후에야 표시할 수 있게 됩니다. 또한 이 과정에서 변환을 위해 추가적으로 `kotlinx.coroutines.reactive`의 종속성을 추가해야 합니다.

이 문제에 대해서는 [stackoverflow](https://stackoverflow.com/questions/71673723/how-to-use-paging-3-library-with-rxjava-3-on-jetpack-compose)에 질문도 올려보았지만 아직 답이 달리지 않았습니다. 다만 공식 문서의 [androidx.paging.compose](https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary?hl=ko)에는 Coroutine Flow를 사용한 함수 목록만 있기 때문에 다른 라이브러리는 지원하지 않는 것으로 보입니다.

이러한 오버헤드가 존재하기 때문에 Compose를 사용한 개발에 데이터 스트림이나 반응형 프로그래밍을 적용한다면 RxJava보다는 Coroutine Flow를 사용하는 것이 호환성 측면에서는 더 유리할 것으로 생각됩니다.

백엔드 개발자와 의논하여 폴링을 통한 데이터 스트림을 지원, 실시간으로 투표 결과의 댓글 목록을 업데이트하는 기능을 추가할 계획이 있습니다. 다만 한정된 서버 자원에서 어느 정도의 간격으로 요청을 보내야 하는지, 추가적인 제한이 필요한지 등의 의논 후 업데이트에 반영할 예정입니다.



