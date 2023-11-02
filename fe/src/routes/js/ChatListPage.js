import ChatMember from "../../component/js/ChatMember";
import GroupIconWithText from "../../component/js/GroupIconWithText";
import NavigationBar from "../../component/js/NavigationBar";
import TopBar from "../../component/js/TopBar";
import { CONSTANT } from "../../constants/Constant";
import styles from "../css/ChatListPage.module.css";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { fetchMemberGroupList } from "../../api/group/MemberGroupList";
import { fetchChatList } from "../../api/chat/ChatList";
import { fetchSSEConnection } from "../../api/chat/SSE";

function ChatListPage() {
  const [memberGroupList, setMemberGroupList] = useState([]);
  const [memberChatList, setMemberChatList] = useState([]);
  const [clickedGroupId, setClickedGroupId] = useState(null);
  const [refreshChatList, setRefreshChatList] = useState(false);

  // 최초 그룹과 채팅목록을 가져오는 useEffect
  useEffect(() => {
    const fetchData = async () => {
      const groupList = await fetchMemberGroupList();
      setMemberGroupList(groupList);
      const chatList = await fetchChatList();
      setMemberChatList(chatList);
      console.log(chatList);
    };
    fetchData();
  }, []);

  // 채팅목록을 가져오는 useEffect (그룹선택시, SSE refresh 요청이 올시 사용한다.)
  useEffect(() => {
    const fetchData = async () => {
      const chatList = await fetchChatList(clickedGroupId);
      setMemberChatList(chatList);
      console.log(chatList);
    };
    fetchData();
  }, [clickedGroupId, refreshChatList]);

  //SSE 연결
  useEffect(() => {
    // SSE 연결 시작
    const closeSSEConnection = fetchSSEConnection(setRefreshChatList);

    // 컴포넌트가 언마운트될 때 SSE 연결을 닫음
    return () => {
      closeSSEConnection();
    };
  }, []);

  return (
    <div className={styles.div}>
      <TopBar topBarName="Chats" icon={CONSTANT.CHATLIST_NOTIFICATION_ICON} />
      <div className={styles.groupListContainer}>
        <div className={styles.groupList}>
          {memberGroupList.map((group) => (
            <GroupIconWithText
              key={group.id}
              imgSrc={group.img}
              groupName={group.name}
              clickedGroupId={setClickedGroupId}
              groupId={group.id}
            />
          ))}
        </div>
      </div>
      <div className={styles.chatList}>
        {memberChatList.map((chat) => (
          <Link
            key={chat.chatRoomId}
            to={`/chats/${chat.chatRoomId}`}
            className={styles.link}
          >
            <ChatMember
              avatarSrc={
                chat.partner.profileImg === null
                  ? CONSTANT.BASIC_CHAT_PROFILE_ICON
                  : chat.partner.profileImg
              }
              nickname={chat.partner.nickname}
              time={chat.messages.lastSendTime}
              message={chat.messages.lastMessage}
              notificationCount={chat.messages.newMessageCount}
              name={chat.partner.name}
            />
          </Link>
        ))}
      </div>
      <NavigationBar />
    </div>
  );
}

export default ChatListPage;
