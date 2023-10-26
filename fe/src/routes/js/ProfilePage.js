import styles from "../css/ProfilePage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import ProfileDetail from "../../component/js/ProfileDetail";
import ToggleButton from "../../component/js/ToggleButton";
import { fetchMemberProfile } from "../../api/member/MemberProfile";
import { useState, useEffect } from "react";
import { fetchMemberLike } from "../../api/member/MemberLikeList";
import ProfileMemberList from "../../component/js/ProfileMemberList";

function ProfilePage() {
  const [memberProfile, setMemberProfile] = useState([]);
  const [memberList, setMemberList] = useState([]);
  const [selected, setSelected] = useState("like");

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchMemberProfile();
      setMemberProfile(data);
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchMemberLike(selected);
      console.log(data);
      setMemberList(data);
    };
    fetchData();
  }, [selected]);

  return (
    <div className={styles.div}>
      <TopBar
        topBarName="Profile"
        icon="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3fd74c1c-5c2e-43fe-8fa5-bd8a70c164da_1697439263404720240?Expires=-62135596800&Signature=GHYl4ivleK~vg7fT0zukZvm1WlpenhPw0LCQXydo-PhJiqoMzhBv~4QQx0T77ROnJTcLHg2UO8UipfwFz3XDaj3AcjRwd97yXnRcvaWhoC8mv3B6TEGl9l3o03Dzvotg20ayxvt95u6JO4sLVkuTTRONQeO507jA~8ph7O0z8NOzPFvnvbm9NriZxypbI4eg96nlmdy0kQnl4vJuZ4V2~pmNzMlku6iIta3~M~6Nw3Zn8sASky9bJ1MXK8IC2IrP-GkdUsc3iNd67upuVmQP-IMA4oNF4Bdx2Fkw2XPW4-GJBP7cVU6ij0JDYZeZD-x-vWtA4lvklVYKBAuHkUrFvg__&Key-Pair-Id=K1P54FZWCHCL6J"
      />

      <ProfileDetail
        name={memberProfile.name}
        nickName={memberProfile.nickName}
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
            avatarSrc={member.profileImg}
            name={member.name}
            nickname={member.nickname}
            introduction={member.introduction}
            toggleButton="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/982ed9a1-ac0e-49e8-b5ce-db1f4f82875a_1697439263404503464?Expires=-62135596800&Signature=wByhy~9rSG41pl5oYzKHPPVri-8uAXuGyroGk3uKK2zktumIECl2yXMkIDMBBEFajk~OqpRFJzRyQSY2~1jXWROf9SfEfYb68E4tld7n8reu8al3aQevAVIipiFojMIA0WU5kLL4AqPi~7NGB9QiT95qD54F6ynU3tvc3e6SNQelOxDviQ0RH2iuQxEknOFlQTzdjEcUnt49ip1T4pI62L-tSX29ePusgpCf4IfU9AQ8GdIoCSJAknHB7f5LGQBYKN8orRonqyH1U7TccDoIOkUAKl22GMbvOyFEXGfqGCG6XwG~bNChUIVw3lUnu9Op-4VUDvapwIVUgMFWlbJYvQ__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
        ))}
      </div>
      <NavigationBar />
    </div>
  );
}

export default ProfilePage;
