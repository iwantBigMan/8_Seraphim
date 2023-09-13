# 8_Seraphim
연락처 앱 개발 구현 - 8은 안으로 굽죠 팀 프로젝트
# 제작 의도 
* 사용자들이 저장한 연락처를 다양한 방법으로 정보를 확인 가능하며, 손쉽게 연락을 할 수 있고 알림 설정을 통해 중요한 연락을 잊어 버리지 않기 위해 제작하게 되었습니다.
## 기술 스택
* Kotlin
* Android Studio
## Notion & Wireframe 
* Notion : https://www.notion.so/8-8-df296dba1d8c41ed9c22b00c5a136014
* Wireframe : https://www.figma.com/file/YC4O1RqjIz6guYCCRpXs1n/8%EC%84%B8%EB%9D%BC%ED%95%8C?type=design&node-id=0-1&mode=design&t=BZ11SUBGit4w8ypU-0

## 시연 연상 링크 
* https://www.youtube.com/watch?v=tDE8srSuk44
## 구현 기능 및 역할 분담
- UI
    - **연락처 리스트 - 궁현**
    - **연락처 추가 - 다민**
    - **상세정보 - 준수**
    - **마이페이지 - 윤희**
    - **즐겨찾기 - 성수**
    
- 기능
    - 필수구현
        - **TabLayout 와 ViewPage - 성수**
        - **연락처 리스트 (ContactListFragment) - 궁현**
        - **연락처 추가 (AddContactDialog or AddContactDialogFragment) - 다민**
        - **상세 정보 (ContactDetailFragment) - 준수**
        - **마이 페이지 (MyPageFragment) - 윤희**
        
    - 추가구현
        - **ItemViewType 변경 적용 - 성수**
        - **Event 시간에 맞춰 Notification 표시 - 다민**
        - **Swipe-to-Action - 윤희**
        - **실제 폰에 있는 연락처 불러오기 - 준수**
        - **`MotionLayout`, `RecyclerView.ItemAnimator` 등의 특수 효과도 활용 - 궁현**

- 발표 준비
    - **발표 - 궁현**
    - **PPT or. PDF 제작 - 다민**
    - **시연영상촬영 - 성수**


