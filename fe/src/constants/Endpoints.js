export const BASE_URL =
  "http://ec2-54-180-81-108.ap-northeast-2.compute.amazonaws.com";
export const BASE_WEB_SOCKET_URL =
  "ws://ec2-54-180-81-108.ap-northeast-2.compute.amazonaws.com:8080";
export const API_ENDPOINTS = {
  LOGIN: (queryString) =>
    `${BASE_URL}/api/login/oauth/naver?code=${queryString}`,

  SIGNUP: `${BASE_URL}/api/auth/signup`,

  MEMBER_GROUP_LIST: (searchWord, memberId) => {
    let queryString = "";

    if (searchWord && memberId) {
      queryString = `?word=${searchWord}&forMember=true}`;
    } else if (searchWord) {
      queryString = `?word=${searchWord}`;
    } else if (memberId) {
      queryString = `?forMember=true`;
    }
    return `${BASE_URL}/api/groups${queryString}`;
  },

  GROUP_DETAIL: (groupId) => `${BASE_URL}/api/groups/${groupId}`,
  GROUP_CREATE: () => `${BASE_URL}/api/groups`,
  ADD_MEMBER_GROUP: (groupId) => `${BASE_URL}/api/groups/${groupId}`,
  GET_MEMBER_PROFILE: `${BASE_URL}/api/members`,
  GET_MEMBER_LIKE: (selected) =>
    `${BASE_URL}/api/members/profiles?selected=${selected}`,
  MEMBER_LIKE: (likedId) => `${BASE_URL}/api/members/${likedId}/likes`,
  MEMBER_BLOCK: (blockId) => `${BASE_URL}/api/members/${blockId}/blocks`,
  OAUTH_API:
    "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=f_R370DxKoDfmyYZB2X0&redirect_uri=http://ec2-54-180-81-108.ap-northeast-2.compute.amazonaws.com/callback",
  MATCHING: (groupId) => `${BASE_URL}/api/chats/random/${groupId}`,
  CHAT_DETAIL: (chatRoomId) => `${BASE_URL}/api/chats/${chatRoomId}`,
  WEB_SOCKET: `${BASE_WEB_SOCKET_URL}/ws`,
  STOMP_SUBSCRIBE: (chatRoomId) => `/sub/chatRoom/${chatRoomId}`,
  STOMP_SEND: `/pub/message`,
  CHAT_LIST: (groupId) =>
    `${BASE_URL}/api/chats${groupId ? `?groupId=${groupId}` : ""}`,
  SSH_CONNECTION: (memberId) => `${BASE_URL}/connect/${memberId}`,
  CHAT_ROOM_DELETE: (chatRoomId) => `${BASE_URL}/api/chats/${chatRoomId}`,
};
