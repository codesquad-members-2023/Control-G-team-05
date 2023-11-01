import NavigationBar from "../../component/js/NavigationBar";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import GroupProfileInformation from "../../component/js/GroupProfileInformation";
import styles from "../css/GroupDetailPage.module.css";
import { useParams } from "react-router-dom";
import { fetchGroupDetail } from "../../api/group/GroupDetail";
import { useEffect, useState } from "react";
import { fetchAddGroupMember } from "../../api/group/AddMemberGroup";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function GroupDetailPage() {
  const [groupDetail, setGroupDetail] = useState([]);
  const groupId = useParams();
  // 회원이 join 버튼을 눌렀을때 리렌더링 하기위해 추가했다.
  const [isMemberAdded, setIsMemberAdded] = useState(false);

  useEffect(() => {
    fetchGroupDetail(groupId)
      .then((data) => {
        setGroupDetail(data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, [groupId, isMemberAdded]);

  const handleMemberAddGroupButtonClick = async (groupId) => {
    const toastMessage = await fetchAddGroupMember(groupId);
    toast(toastMessage);
    setIsMemberAdded(true);
  };

  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Group Profile" returnLink="/groups" />
      <GroupProfileInformation
        clickEvent={() => handleMemberAddGroupButtonClick(groupId)}
        groupImgSrc={groupDetail.img}
        groupName={groupDetail.name}
        memberCount={groupDetail.headCount}
      />
      <NavigationBar />
    </div>
  );
}

export default GroupDetailPage;
