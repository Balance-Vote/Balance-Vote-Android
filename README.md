
![playstore](https://user-images.githubusercontent.com/37128456/173813087-615167a3-a8ca-461d-921a-c6900d396199.png)

# BalanceVote

![GitHub release (latest by date)](https://img.shields.io/github/v/release/Balance-Vote/Balance-Vote-Android)
![GitHub last commit](https://img.shields.io/github/last-commit/Balance-Vote/Balance-Vote-Android)

(구글 플레이 검토중)

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

애플리케이션의 UI 레이어는 모두 Jetpack Compose를 사용하여 구현하였습니다.

### 선언적 그래픽

위에서 그려진 그래프는 Compose의 `Canvas` 객체를 사용하여 구현하였습니다. 

### Navigation

Navigation 또한 기존의 Fragment 방식 대신 Compose에서 `Composable` 간의 이동으로 구현하였습니다.

## Hilt


## RxJava



