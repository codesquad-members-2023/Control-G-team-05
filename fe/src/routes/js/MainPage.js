import react from "react";
import styles from "../css/MainPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import SearchInput from "../../component/js/SearchInput";
import GroupIconWithTextAndButton from "../../component/js/GroupIconWithTextAndButton";
import DividerLine from "../../component/js/DividerLine";
import { fetchMemberGroupList } from "../../api/group/MemberGroupList";
import { useEffect, useState } from "react";
import { CONSTANT } from "../../constants/Constant";

function MainPage() {
  const [allGroups, setAllGroups] = useState([]);
  const [filteredGroups, setFilteredGroups] = useState([]);
  const [searchWord, setSearchWord] = useState("");

  useEffect(() => {
    fetchMemberGroupList().then((data) => {
      setAllGroups(data);
      setFilteredGroups(data);
    });
  }, []);

  //필터링 된 그룹 설정
  useEffect(() => {
    const result = allGroups.filter((group) =>
      group.name.toLowerCase().includes(searchWord.toLowerCase())
    );
    setFilteredGroups(result);
  }, [searchWord, allGroups]);

  return (
    <div className={styles.div}>
      <TopBar topBarName="Groups" />
      <SearchInput setSearchWord={setSearchWord} />
      <div className={styles.groupList}>
        <div>
          {filteredGroups.map((group, index) => (
            <react.Fragment key={group.id}>
              <GroupIconWithTextAndButton
                groupId={group.id}
                groupImgSrc={group.img}
                groupName={group.name}
                vectorIconSrc={CONSTANT.RANDOM_CHAT_START_BUTTON}
              />
              {index !== filteredGroups.length - 1 && <DividerLine />}
            </react.Fragment>
          ))}
        </div>
      </div>
      <NavigationBar />
    </div>
  );
}

export default MainPage;
