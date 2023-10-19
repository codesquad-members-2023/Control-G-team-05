import NavigationBar from "../../component/js/NavigationBar";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import GroupProfileInformation from "../../component/js/GroupProfileInformation";
import styles from "../css/GroupDetailPage.module.css";

function GroupDetailPage() {
  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Group Profile" returnLink="/groups" />
      <GroupProfileInformation
        groupImgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/006135da-e13c-46d3-89c3-88b15d7a8696_1697439278585209072?Expires=-62135596800&Signature=Y9O5jErsMQ~mtiybMdmEijNxIA1FgtLH14QB9zsK5W9TfRVAtkkpbfZY6giEgPJKh643MKvdFixFIsEssuIKQelZi46Xan6Oszjj6QukzGZ61E0oMINjGMBZd9td98dq7sJNNnP0mHbAHzZunf2AMHkDYvs05n8SvrkDwlWeu99UV~gR~~Kunnnku8fqmPOMKnujFCNmpqhfiVJp0Pm~wi1GolBzCOB6bFO52LA7PpLbL-FNHGMk5S~irGbyXb9T7zwfr1JpkOJWSD9YF4twgWG0e3WomrLt4V7Ouc0zyeRLJEuXE55P9Uof899M1eGE18WVVM5Vt29EwLw745DhQQ__&Key-Pair-Id=K1P54FZWCHCL6J"
        groupName="Code Squad"
        memberCount="5.3"
      />
      <NavigationBar />
    </div>
  );
}

export default GroupDetailPage;
