# 몰입캠프 2주차 프로젝트 "밥 메이트"
## 팀원
* 박정웅 | https://github.com/yeolia327
* 이지원 | https://github.com/Rudolf0328
* [서버] https://github.com/Rudolf0328/madcamp_week2_server

## 개요
```
"한국인은 밥심"이라는 문구를 주제로 앱을 구상했다.  
"금강산도 식후경", "먹고 죽은 귀신은 떼깔도 좋다" 등 한국에는 밥과 관련된 많은 속담이 존재한다. 이를 통해 우리가 얼마나 밥을 중요시 하는지 알 수 있다.  
요즘은 많은 사람들이 이전만큼 밥을 챙겨먹지 않고 혼자서 먹는 문화가 늘어나고 있다. 당연히 혼밥을 존중하지만 밥은 역시 여럿이 같이 먹어야 더 맛있다고 생각한다.  
더 많은 사람들이 모여서 밥을 더 잘 챙겨먹었으면 하는 마음을 담아 "밥 메이트"를 만들게 되었다.
```

## 설명
```
앞서 말한 듯이 "밥 메이트"는 "밥"을 주제로 많은 사람들이 모이는 공간이다.  
자신이 먹은 맛집, 혹은 음식에 관한 추억을 공유하고 싶은 사람들, 다른 사람들의 음식, 식당 정보를 보고 싶은 사람들, 함께 밥먹을 사람을 찾고 있는 사람들이 우리의 앱을 유용하게 사용할 수 있다.  
피드를 올려 내가 먹은 음식의 사진, 맛있었던 식당의 정보, 혹은 내가 만든 음식의 레시피를 공유할 수 있다.
또한 다른 사람들이 올린 음식 사진들로 가득찬 릴스를 본다면 마음까지 배불러지는 경험을 할 수 있다.
마지막으로 먹고 싶은 메뉴, 사람 수를 정해 밥 메이트를 모집하면 음식에 대하여 마음이 맞는 사람들과 모여서 함께 식사를 할 수 있다. 
```

## 각 기능별 상세 설명
### 로그인
```
카카오톡을 통하여 로그인 할 수도, 앱에서 따로 회원가입을 통해 로그인할 수도 있다.
원하는 방식으로 로그인을 진행하면 자신의 정보를 포함하고 있는 프로필을 확인할 수 있다.
```

### 피드 올리기
```
본인이 공유하고 싶은 음식과 관련되 추억을 공유할 수 있다. 의식주가 인간의 기본 생존요건인 만큼 모든 사람은 음식과 관련된 추억이 많을 것이다. 그 차고 넘치는 추억을 보관하기에 "밥 메이트"의 피드가 적절히 사용될 수 있다.
원하는 사진을 올리고 내용을 적은 뒤 올리기 버튼을 누르면 내 화면에 피드가 추가 되고 이 앱을 사용하는 모두가 피드를 확인할 수 있게 된다.
혹시나 지우고 싶은 추억이 생기거나 수정할 일이 생기면 각 피드에 있는 버튼을 통해 피드를 수정, 삭제할 수 있다.
```

### 릴스 확인하기
```
앱을 사용하는 모두가 올린 피드를 확인할 수 있다. 쉽게 확인할 수 있도록 사진들로 구성되어있다. 사용자들의 피드를 탐색하다가 맘에 드는 사진, 혹은 궁금한 사진을 누르면 이 피드의 내용과 작성자를 확인할 수 있다.
혹시 나와 같은 취향을 가진 사람을 발견한다면 소중한 만남으로 이어질 수도 있다.
```

### 밥 메이트 구하기
```
간단한 정보 입력을 통해 앱의 사용자 안에서 함께 밥먹을 사람들을 구할 수 있다.
먹고 싶은 음식이 있다면 새로운 팟 만들기를 통해 원하는 음식과 비슷한 이미지를 고르고, 먹고 싶은 음식, 원하는 인원수, 시간을 입력하면 3번째 탭에 밥 먹을 사람을 구하는 내용이 올라간다.
음식 이름 아래에 적혀있는 숫자와 동그라미를 통해 몇명을 모을 예정이고, 누가 참가 중인지, 어떤 사람이 모집하였는지 확인할 수 있다. 각 동그라미를 누르면 참가한 사람들의 프로필도 확인할 수 있다.
어떤 음식을 먹을지 정하지 못했다면 다른 사람들이 만들어 놓은 팟에 들어가는 것도 좋은 방법이다.
팟들을 구경하다가 끌리는 메뉴가 있다면 선택해 참가할 수 있다. 더 먹고 싶은 메뉴가 보인다면 원래 들어간 팟에서 나오고 다시 들어갈 수 있으니 걱정하지 않아도 된다.
```

### 기술 설명
```
많은 기능들을 구현하기 위해 많은 기술을 사용하였으므로 모두 설명하는 것은 쉽지 않을 것이다.
우리가 사용한 기술 중 가장 자랑하고 싶은 부분을 설명하려고 한다.

<Frontend>
* 사용한 기술 : android studio (java, kotlin), Retrofit2
* 이미지 올리기
* Retrofit2

<Backend>
* 사용한 기술 : node.js, mongodb
* api list 정리 : 많은 기술이 필요한 건 아니지만 frontend와의 효율적인 협업을 위해 github에 api list를 보기 쉽게 정리하였다. 협업을 진행한 만큼 사소해보여도 중요한 부분이라 생각되어 자랑하고 싶다.
* db diagram : DB를 구축하기 위해 db diagram을 그렸다. 나중에 table이 꼬이는 것을 방지하기 위해 처음부터 꼼꼼하게 생각하여 정리해둔 것이라 자랑하고 싶다.
```

## 한 줄 평
### 박정웅
```
앞 자리 싫다...
```
### 이지원
```
frontend와 backend로 거의 나누어져 진정한 협업을 느낄 수 있는 시간이었다. 또한 내 적성에 backend가 더 맞다는 것을 알 수 있는 시간이었다.... frontend에서 도망쳐야지,,,
```
