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

function SearchPage() {
  const [allGroups, setAllGroups] = useState([]);
  const [filteredGroups, setFilteredGroups] = useState([]);
  const [searchWord, setSearchWord] = useState("");

  useEffect(() => {
    fetchGroupList().then((data) => {
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
      <TopBar
        topBarName="Search"
        icon="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3173bc89-f47f-4157-a4f9-de5d512156af_1697439276210455055?Expires=-62135596800&Signature=DhFgbo~gFIC93sasVDH8FdTwIwVMw3xKz8vjdQ9lz4f1FmIV4gsblUugO6be3yn7-pTOesLIlS36VXoEyTY2G270WlJfq-j7~rKYcabQUx8prWN9PepZbAiZT~~mkgq3TxaoiW8SOq9tg1tc4R64TTRFlYzg3YZFdJ8kK25eIQb5rTrh0Tj9OQBckt-6ehW0JLGqabWqv2WL9fGcoI7KRLFpXTEgUUcJpJuZXGLPRvicToPyoXb66BUfcrE-iYmpmAQH7sDZX42iZvVk3s14ffKMaCPy~bO6vPy1aGe1LZSEfl6a96tbjXYuRBVTVhfqBTMdgJK46nc5FJG9qT6VwQ__&Key-Pair-Id=K1P54FZWCHCL6J"
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
