import styles from "../css/ProfilePage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import ProfileDetail from "../../component/js/ProfileDetail";
import ToggleButton from "../../component/js/ToggleButton";
import { fetchMemberProfile } from "../../api/member/MemberProfile";
import { useState, useEffect } from "react";
import { fetchMemberList } from "../../api/member/MemberList";
import ProfileMemberList from "../../component/js/ProfileMemberList";
import { CONSTANT } from "../../constants/Constant";

function ProfilePage() {
  const [memberProfile, setMemberProfile] = useState([]);
  const [memberList, setMemberList] = useState([]);
  const [selected, setSelected] = useState("like");
  // modal의 특정 버튼이 눌렸을때 멤버리스트를 다시 렌더링 하기위한 state
  const [modalButtonClicked, setModalButtonClicked] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchMemberProfile();
      setMemberProfile(data);
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchMemberList(selected);
      setMemberList(data);
    };
    fetchData();
  }, [selected, modalButtonClicked]);

  return (
    <div className={styles.div}>
      <TopBar topBarName="Profile" icon={CONSTANT.PROFILE_EDIT_ICON} />

      <ProfileDetail
        name={memberProfile.name}
        nickname={memberProfile.nickname}
        age={memberProfile.age}
        gender={memberProfile.gender}
        statusMessage={memberProfile.introduction}
        profileImage={memberProfile.profileImg}
        likedCount={memberProfile.likeCount}
      />
      <ToggleButton
        leftLabel="like"
        rightLabel="matched"
        buttonSelectedFunction={setSelected}
      />
      <div className={styles.memberList}>
        {memberList.map((member, index) => (
          <ProfileMemberList
            key={index}
            memberId={member.id}
            avatarSrc={member.profileImg}
            name={member.name}
            nickname={member.nickname}
            introduction={member.introduction}
            toggleButton={CONSTANT.PROFILE_MODAL_BUTTON}
            selected={selected}
            modalButtonClicked={modalButtonClicked}
            setModalButtonClicked={setModalButtonClicked}
          />
        ))}
      </div>
      <NavigationBar />
    </div>
  );
}

export default ProfilePage;
