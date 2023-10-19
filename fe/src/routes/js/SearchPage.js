import styles from "../css/SearchPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import SearchInput from "../../component/js/SearchInput";
import GroupIconWithTextAndButton from "../../component/js/GroupIconWithTextAndButton";
import DividerLine from "../../component/js/DividerLine";
import { Link } from "react-router-dom";

function SearchPage() {
  return (
    <div className={styles.div}>
      <TopBar
        topBarName="Search"
        icon="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3173bc89-f47f-4157-a4f9-de5d512156af_1697439276210455055?Expires=-62135596800&Signature=DhFgbo~gFIC93sasVDH8FdTwIwVMw3xKz8vjdQ9lz4f1FmIV4gsblUugO6be3yn7-pTOesLIlS36VXoEyTY2G270WlJfq-j7~rKYcabQUx8prWN9PepZbAiZT~~mkgq3TxaoiW8SOq9tg1tc4R64TTRFlYzg3YZFdJ8kK25eIQb5rTrh0Tj9OQBckt-6ehW0JLGqabWqv2WL9fGcoI7KRLFpXTEgUUcJpJuZXGLPRvicToPyoXb66BUfcrE-iYmpmAQH7sDZX42iZvVk3s14ffKMaCPy~bO6vPy1aGe1LZSEfl6a96tbjXYuRBVTVhfqBTMdgJK46nc5FJG9qT6VwQ__&Key-Pair-Id=K1P54FZWCHCL6J"
        linkPage="/groups/create"
      />
      <div className={styles.searchContainer}>
        <SearchInput />
        <div className={styles.groupContainer}>
          <Link to="/groups/detail">
            <GroupIconWithTextAndButton
              groupImgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
              groupName="code Squad"
            />
          </Link>
          <DividerLine />
          <GroupIconWithTextAndButton
            groupImgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4d41b79a-da1b-4eaf-a83f-8590fd2886a4_1697439097327324168?Expires=-62135596800&Signature=Y~B3Hv3PstAeGkQw00PURW1DbnZULSsI14KRbmoGXElXzmlfX~iiW9F6Xxlj16G2d6kcNEIDk7H5ABKaudw9azFD7ZlYeY5-yroMg3U5jDgQaY-o~uIzNvQcywzRHP5PNiqYXPnKq6bhRXU7n2rgihNEKW1FtR2bbGSL3r9K-ludoG2vK2T-0T9BrlhsA9mwMRfM845O1lW25pcTIeaGGjgl-n5AACTOEYQWMR7Dl7bSnB8zZ8FnkEQZhv6nKK55O6e5vYIC-0IT-QzOKVyD4TsPQpSJfwPwmFjJDNL3CJjkiOub2DJw4SoelcrKT2YGf3ceU4NwRtTeHPbuTlfeWA__&Key-Pair-Id=K1P54FZWCHCL6J"
            groupName="Naver"
          />
          <DividerLine />
        </div>
      </div>
      <NavigationBar />
    </div>
  );
}

export default SearchPage;
