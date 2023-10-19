import styles from "../css/ProfilePage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import ProfileDetail from "../../component/js/ProfileDetail";
import ToggleButton from "../../component/js/ToggleButton";
import ChatMember from "../../component/js/ChatMember";
import DividerLine from "../../component/js/DividerLine";

function ProfilePage() {
  return (
    <div className={styles.div}>
      <TopBar
        topBarName="Profile"
        icon="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3fd74c1c-5c2e-43fe-8fa5-bd8a70c164da_1697439263404720240?Expires=-62135596800&Signature=GHYl4ivleK~vg7fT0zukZvm1WlpenhPw0LCQXydo-PhJiqoMzhBv~4QQx0T77ROnJTcLHg2UO8UipfwFz3XDaj3AcjRwd97yXnRcvaWhoC8mv3B6TEGl9l3o03Dzvotg20ayxvt95u6JO4sLVkuTTRONQeO507jA~8ph7O0z8NOzPFvnvbm9NriZxypbI4eg96nlmdy0kQnl4vJuZ4V2~pmNzMlku6iIta3~M~6Nw3Zn8sASky9bJ1MXK8IC2IrP-GkdUsc3iNd67upuVmQP-IMA4oNF4Bdx2Fkw2XPW4-GJBP7cVU6ij0JDYZeZD-x-vWtA4lvklVYKBAuHkUrFvg__&Key-Pair-Id=K1P54FZWCHCL6J"
      />

      <ProfileDetail
        name="김찬연"
        nickName="기무찬욘"
        age={26}
        gender="female"
        statusMessage="life goes on"
        profileImage="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3015c988-3cf4-47ce-a8f7-2d4f864d1269_1697439263403986859?Expires=-62135596800&Signature=mGAOBonCKbB9CzafFKf4aFOnpGKfImTe5fLM5Hmitc-WxlgIE2-~3iI4mB8KbY10O53Y1EceKEv1cZXJ4pVzNkp5jQZIc~Cg1jepu4EmybEbh3DBA5ZvXoCGa6KGN~HQ583npjckb5bYbbb1NEQh5AWWhLUVXIkFzcTkzjivrfXsCWaaEd-ywhiyYWfsBYzwzfO5zaewL2pAW0ovqSaStqthpIjGduybcyXDnjgD11bLZyqMCd1GeCjhPRMbgHxwxE9YEgVfYfSxqiBllYgdQry8fW7Yfw5xSWTG3-mGqlrJaO~Z-pYxwLveUV9kC1q1ph1pFt3vN15joQ7wiWL9Sg__&Key-Pair-Id=K1P54FZWCHCL6J"
        likedCount="100"
      />
      <ToggleButton />
      <div className={styles.contentcontentBlocksmall}>
        <ChatMember
          avatarSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3e8070f1-02fd-4d00-9ce5-8e71a52cd40f_1697439263404249404?Expires=-62135596800&Signature=pkJx9aVyJWqoMbdeDzJ2wDSIYgYSuSAXz6Y3EAe3oLdkdWAI-Ej6ioEVHNBskhVhHu2knF5lFAczVJAdkhpJtwX~PR6mHji32RnqLYT7CcW7hqp~SGKz9y6NUdSXQcwETGpgVfGvUTTx2P1WHPldFnH01MbN9b~9WXgAvsA449Z0zdKRtbXA7ZARIPhMX0OQUYUb-2EWWu4OtuCYzPMSB0gmG2lu4o6yP56Fa~9zLPPyLwI8yM-6QX6uhBCuVD1Hrf5dNhlxEudaQUcXPzfHVq9uFpkdM2PTf9MJxVPrLKblk4ZhUkw30GAABYA0-SPzVDWB~nt2szVoLystBMIeGw__&Key-Pair-Id=K1P54FZWCHCL6J"
          name="charlie"
          message="안녕하세요~! 친구 구해요"
          toggleButton="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/982ed9a1-ac0e-49e8-b5ce-db1f4f82875a_1697439263404503464?Expires=-62135596800&Signature=wByhy~9rSG41pl5oYzKHPPVri-8uAXuGyroGk3uKK2zktumIECl2yXMkIDMBBEFajk~OqpRFJzRyQSY2~1jXWROf9SfEfYb68E4tld7n8reu8al3aQevAVIipiFojMIA0WU5kLL4AqPi~7NGB9QiT95qD54F6ynU3tvc3e6SNQelOxDviQ0RH2iuQxEknOFlQTzdjEcUnt49ip1T4pI62L-tSX29ePusgpCf4IfU9AQ8GdIoCSJAknHB7f5LGQBYKN8orRonqyH1U7TccDoIOkUAKl22GMbvOyFEXGfqGCG6XwG~bNChUIVw3lUnu9Op-4VUDvapwIVUgMFWlbJYvQ__&Key-Pair-Id=K1P54FZWCHCL6J"
        />
        <ChatMember
          avatarSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3e8070f1-02fd-4d00-9ce5-8e71a52cd40f_1697439263404249404?Expires=-62135596800&Signature=pkJx9aVyJWqoMbdeDzJ2wDSIYgYSuSAXz6Y3EAe3oLdkdWAI-Ej6ioEVHNBskhVhHu2knF5lFAczVJAdkhpJtwX~PR6mHji32RnqLYT7CcW7hqp~SGKz9y6NUdSXQcwETGpgVfGvUTTx2P1WHPldFnH01MbN9b~9WXgAvsA449Z0zdKRtbXA7ZARIPhMX0OQUYUb-2EWWu4OtuCYzPMSB0gmG2lu4o6yP56Fa~9zLPPyLwI8yM-6QX6uhBCuVD1Hrf5dNhlxEudaQUcXPzfHVq9uFpkdM2PTf9MJxVPrLKblk4ZhUkw30GAABYA0-SPzVDWB~nt2szVoLystBMIeGw__&Key-Pair-Id=K1P54FZWCHCL6J"
          name="wiz"
          message="안녕하세요~! 아무나 구해요"
          toggleButton="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/982ed9a1-ac0e-49e8-b5ce-db1f4f82875a_1697439263404503464?Expires=-62135596800&Signature=wByhy~9rSG41pl5oYzKHPPVri-8uAXuGyroGk3uKK2zktumIECl2yXMkIDMBBEFajk~OqpRFJzRyQSY2~1jXWROf9SfEfYb68E4tld7n8reu8al3aQevAVIipiFojMIA0WU5kLL4AqPi~7NGB9QiT95qD54F6ynU3tvc3e6SNQelOxDviQ0RH2iuQxEknOFlQTzdjEcUnt49ip1T4pI62L-tSX29ePusgpCf4IfU9AQ8GdIoCSJAknHB7f5LGQBYKN8orRonqyH1U7TccDoIOkUAKl22GMbvOyFEXGfqGCG6XwG~bNChUIVw3lUnu9Op-4VUDvapwIVUgMFWlbJYvQ__&Key-Pair-Id=K1P54FZWCHCL6J"
        />
      </div>
      <NavigationBar />
    </div>
  );
}

export default ProfilePage;
