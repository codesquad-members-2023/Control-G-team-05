import styles from "../css/MainPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";
import SearchInput from "../../component/js/SearchInput";
import GroupIconWithTextAndButton from "../../component/js/GroupIconWithTextAndButton";
import DividerLine from "../../component/js/DividerLine";

function MainPage() {
  return (
    <div className={styles.div}>
      <TopBar topBarName="Groups" />
      <div className={styles.groupList}>
        <SearchInput />
        <div className={styles.contactStack}>
          <GroupIconWithTextAndButton
            groupImgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
            groupName="code Squad"
            vectorIconSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4fbb8d44-8109-413f-b5c6-ac332ef15dd3_1697593141402360165?Expires=-62135596800&Signature=K8jngQR0vyPUOVpFBr6ca3jr9VPzcU2Fog2EbqUf2abHNNI-eihJjhYoA8r9VInImjlBBadGPOYFWxJYeF3pUA3lWIik~gpvGHJkk0IAbr~AbfG-3eXYk9FttV3UHCoPU-uZk1weXA4~YWgYVjfSAhyEvub~rsqo9~OQnAhnwOcpIsejATqOkk~SRiHsUNl54EYwutJ2Btax5MdgJMXG6509cs0oo0AziNAWNXrWW4WlFL0RGpKlEwRKu3MqE7USoZbvcFK~rhRVdyp6xzwNGvO0eFqOsbsb5DVSz7~xUXCJQ4eC4epXOI1O0WERLn0Ic-SvwyWLy6oYWb-wq9BhGg__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
          <DividerLine />
          <GroupIconWithTextAndButton
            groupImgSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
            groupName="code Squad"
            vectorIconSrc="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4fbb8d44-8109-413f-b5c6-ac332ef15dd3_1697593141402360165?Expires=-62135596800&Signature=K8jngQR0vyPUOVpFBr6ca3jr9VPzcU2Fog2EbqUf2abHNNI-eihJjhYoA8r9VInImjlBBadGPOYFWxJYeF3pUA3lWIik~gpvGHJkk0IAbr~AbfG-3eXYk9FttV3UHCoPU-uZk1weXA4~YWgYVjfSAhyEvub~rsqo9~OQnAhnwOcpIsejATqOkk~SRiHsUNl54EYwutJ2Btax5MdgJMXG6509cs0oo0AziNAWNXrWW4WlFL0RGpKlEwRKu3MqE7USoZbvcFK~rhRVdyp6xzwNGvO0eFqOsbsb5DVSz7~xUXCJQ4eC4epXOI1O0WERLn0Ic-SvwyWLy6oYWb-wq9BhGg__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
          <DividerLine />
        </div>
      </div>
      <NavigationBar />
    </div>
  );
}

export default MainPage;
