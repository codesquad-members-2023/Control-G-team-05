import { TextField } from "@mui/material";
import styles from "../css/MainPage.module.css";
import TopBar from "../../component/js/TopBar";
import NavigationBar from "../../component/js/NavigationBar";

function MainPage() {
  return (
    <div className={styles.div}>
      <TopBar />
      <div className={styles.groupList}>
        {/* 다른 스타일 textField 라 일단은 컴포넌트로 안만듬 */}
        <TextField
          className={styles.input}
          color="primary"
          label="Search"
          sx={{ width: 327 }}
          variant="standard"
        />
        <div className={styles.contactStack}>
          <div className={styles.groupContainer}>
            <div className={styles.avatar}>
              <img
                className={styles.groupImg}
                alt=""
                src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/620e3538-14da-4923-a412-0d4a7ce9430a_1697439097326938817?Expires=-62135596800&Signature=h-Fi9sA8mlHIwtn24BaozkabBCXLISzr4fjYf-WmbxcSi~Z7DdB1Xzul~LBPsLqyeEMvXdR5n5fom5fx61HjGasKOsvas5rZ~NazzS4FP0u~o~0HHeQW2Z0y6z6npU9UQuayc6hsTYfCvc70-PBF~e0kF7tLjAH9Rt7FKHVni26o~GJpqSq6mtnUQ~IPoJI31p1ECgBWmwr7Kd27bi63MeeSNTnAPi14fBSL~hz0oK9079hz5SsKa7itWrBJnr8YRJT2ejSg8xM9552GRZtPODW3lzKgMOAJIMVweOjxn1VIW002T0VUzkfMIyf~9UIg5BMV6GhP5vFzr4XN8sLqAw__&Key-Pair-Id=K1P54FZWCHCL6J"
              />
            </div>
            <div className={styles.groupDetailContainer}>
              <div className={styles.groupName}>Code squad</div>
              <img
                className={styles.vectorIcon}
                alt=""
                src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/d8819cb8-7539-498f-8100-9cbda0de0f95_1697439097327121461?Expires=-62135596800&Signature=fLKeapvtZtBHXLvPVQWBlLHKSOIzpoyIGeFNmpTelCgbECe4B4i0PkzhlHHQ8bpDOSWxggnxBgEid9LR3KhZKjxc~FaCfnH7p-zM~m4NGZZvWBHsYxtTrPz5hvrYWEdeTeTWh47lN1toJKnbwycDInq~LQKW5ENRgN5F8oGkTttA1NNHU5zV0KA7o1HPZ52uA2Q3L6aAPFLoDQ6jeVSzeXurXWLB5klacMckVs64SqO5WnHyHbxLdX5MPHQuEOz71mG5BuuarbnPxJoGUwlL45zTGaxxdM4zrr2uVGcTRFVNCjvdm6lL1mw7qX0JgKz2S9s4wDGokI7DU-AHHG--Iw__&Key-Pair-Id=K1P54FZWCHCL6J"
              />
            </div>
          </div>
          <div className={styles.divider}>
            <img className={styles.dividerChild} alt="" src="/vector-1.svg" />
          </div>
        </div>
      </div>
      <NavigationBar />
    </div>
  );
}

export default MainPage;
