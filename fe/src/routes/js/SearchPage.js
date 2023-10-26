import react from "react";
import styles from "../css/SearchPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import SearchInput from "../../component/js/SearchInput";
import GroupIconWithTextAndButton from "../../component/js/GroupIconWithTextAndButton";
import DividerLine from "../../component/js/DividerLine";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { fetchGroupList } from "../../api/group/GroupList";
import { CONSTANT } from "../../constants/Constant";

function SearchPage() {
  const [allGroups, setAllGroups] = useState([]);
  const [filteredGroups, setFilteredGroups] = useState([]);
  const [searchWord, setSearchWord] = useState("");

  useEffect(() => {
    const fetchAndSetGroups = async () => {
      const data = await fetchGroupList();
      setAllGroups(data);
      setFilteredGroups(data);
    };
    fetchAndSetGroups();
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
      <TopBar
        topBarName="Search"
        icon={CONSTANT.GROUP_ADD_ICON}
        linkPage="/groups/create"
      />
      <SearchInput setSearchWord={setSearchWord} />
      <div className={styles.groupContainer}>
        <div>
          {filteredGroups.map((group, index) => (
            <react.Fragment key={group.id}>
              <Link
                to={`/groups/${group.id}`}
                style={{ color: "inherit", textDecoration: "none" }}
              >
                <GroupIconWithTextAndButton
                  groupImgSrc={group.img}
                  groupName={group.name}
                />
              </Link>
              {index !== filteredGroups.length - 1 && <DividerLine />}
            </react.Fragment>
          ))}
        </div>
      </div>
      <NavigationBar />
    </div>
  );
}

export default SearchPage;
