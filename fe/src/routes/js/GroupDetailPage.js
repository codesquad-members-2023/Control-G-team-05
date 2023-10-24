import NavigationBar from "../../component/js/NavigationBar";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import GroupProfileInformation from "../../component/js/GroupProfileInformation";
import styles from "../css/GroupDetailPage.module.css";
import { useParams } from "react-router-dom";
import { fetchGroupDetail } from "../../api/group/GroupDetail";
import { useEffect, useState } from "react";

function GroupDetailPage() {
  const [groupDetail, setGroupDetail] = useState([]);
  const { groupId } = useParams();

  useEffect(() => {
    fetchGroupDetail(groupId)
      .then((data) => {
        setGroupDetail(data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, [groupId]);

  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Group Profile" returnLink="/groups" />
      <GroupProfileInformation
        groupImgSrc={groupDetail.img}
        groupName={groupDetail.name}
        memberCount={groupDetail.headCount}
      />
      <NavigationBar />
    </div>
  );
}

export default GroupDetailPage;
