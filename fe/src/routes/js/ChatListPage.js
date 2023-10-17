import ChatMember from "../../component/js/ChatMember";
import GroupIconWithText from "../../component/js/GroupIconWithText";
import NavigationBar from "../../component/js/NavigationBar";
import TopBar from "../../component/js/TopBar";
import styles from "../css/ChatListPage.module.css";

function ChatListPage() {
  return (
    <div className={styles.div}>
      <TopBar
        topBarName="Chats"
        icon="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/f3da5d67-24d5-4ef7-82ca-75525805fc3a_1697439258068277195?Expires=-62135596800&Signature=RFFKtdnjAeY5--u9G0WB3Zz4OBH77IfbjtFlgYdZNlImwd6Ycl2R5wyg62~Nj36vQPD-rBXfsoyKlprNa~Yb70CdPsX4DQSpJnCLPhcnh30Xu5JsVt8czvIWUL9tXa50Q8sMX97AN1VNmsb1IebR9djVIodjcugpoOnxy7a2kJPjJazhKkYI9w2szkydkkWZ06V2HlcYbaTpTkjEw8fkWF6ZORPsfInFUzIZfHKqbneSqx5EI9SBmr0niHtBMWsDe0S2I3t9mjo9R1WD0XyLCHEHUFSU32ETxSBknCho6hsp0vKwa3oAcfmzCueNwH0l1AVZIkiGEzqxgKuIYpyQ7A__&Key-Pair-Id=K1P54FZWCHCL6J"
      />
      <div className={styles.groupListContainer}>
        <GroupIconWithText
          imgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
          groupName="CodeSquad"
        />
        <GroupIconWithText
          imgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
          groupName="CodeSquad"
        />
      </div>
      <div className={styles.chatList}>
        <ChatMember
          avatarSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e6cb9e73-a3d4-4eb2-a68e-d01372a032f5_1697439258067884952?Expires=-62135596800&Signature=GdBVX5QCwWsIiDwccsMIxJFfAYhfKDztXKeT4dZN~~4NerTml7Mdy6bGuHYrcgW~oYcgHV2yWsRz6j7xDPdlpYsdUWtre-k5xbX8qET~CknWxz3IjF6xCagtm3lqTGGT2CNh80od4lDRBh6C2PFUWeZ-dFD96QRwyInGMuiYNnM0Od5VWPhOxKszPU03n-9XC-I4ZcS0lqSQfOmqVd2PnsVnDT1PXBWyCOPrC34wzzwrrQEAzxLvA49JZXZrUMkHy~bXpDyB6PzbL5udzglagCsEsGvCTibuO3ww0ZQFYLG4qku7kZfAwK8suVo3y3IDs2CNAE5mY1BUNGdt4~z1IA__&Key-Pair-Id=K1P54FZWCHCL6J"
          name="joyasdasd"
          time="3m ago"
          message="Good morning, did you sleep well?"
          notificationCount="12"
        />
        <ChatMember
          avatarSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e6cb9e73-a3d4-4eb2-a68e-d01372a032f5_1697439258067884952?Expires=-62135596800&Signature=GdBVX5QCwWsIiDwccsMIxJFfAYhfKDztXKeT4dZN~~4NerTml7Mdy6bGuHYrcgW~oYcgHV2yWsRz6j7xDPdlpYsdUWtre-k5xbX8qET~CknWxz3IjF6xCagtm3lqTGGT2CNh80od4lDRBh6C2PFUWeZ-dFD96QRwyInGMuiYNnM0Od5VWPhOxKszPU03n-9XC-I4ZcS0lqSQfOmqVd2PnsVnDT1PXBWyCOPrC34wzzwrrQEAzxLvA49JZXZrUMkHy~bXpDyB6PzbL5udzglagCsEsGvCTibuO3ww0ZQFYLG4qku7kZfAwK8suVo3y3IDs2CNAE5mY1BUNGdt4~z1IA__&Key-Pair-Id=K1P54FZWCHCL6J"
          name="joy"
          time="3m ago"
          message="Good morning, did you sleep well?"
          notificationCount="12"
        />
      </div>
      <NavigationBar />
    </div>
  );
}

export default ChatListPage;
