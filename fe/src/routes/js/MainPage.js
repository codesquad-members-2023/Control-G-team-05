import react from "react";
import styles from "../css/MainPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import SearchInput from "../../component/js/SearchInput";
import GroupIconWithTextAndButton from "../../component/js/GroupIconWithTextAndButton";
import DividerLine from "../../component/js/DividerLine";
import { fetchMemberGroupList } from "../../api/group/MemberGroupList";
import { useEffect, useState } from "react";

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
                groupImgSrc={group.img}
                groupName={group.name}
                vectorIconSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4fbb8d44-8109-413f-b5c6-ac332ef15dd3_1697593141402360165?Expires=-62135596800&Signature=K8jngQR0vyPUOVpFBr6ca3jr9VPzcU2Fog2EbqUf2abHNNI-eihJjhYoA8r9VInImjlBBadGPOYFWxJYeF3pUA3lWIik~gpvGHJkk0IAbr~AbfG-3eXYk9FttV3UHCoPU-uZk1weXA4~YWgYVjfSAhyEvub~rsqo9~OQnAhnwOcpIsejATqOkk~SRiHsUNl54EYwutJ2Btax5MdgJMXG6509cs0oo0AziNAWNXrWW4WlFL0RGpKlEwRKu3MqE7USoZbvcFK~rhRVdyp6xzwNGvO0eFqOsbsb5DVSz7~xUXCJQ4eC4epXOI1O0WERLn0Ic-SvwyWLy6oYWb-wq9BhGg__&Key-Pair-Id=K1P54FZWCHCL6J"
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
