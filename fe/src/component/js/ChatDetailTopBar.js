import React from "react";
import styles from "../css/ChatDetailTopBar.module.css";
import { Link } from "react-router-dom";

function ChatDetailTopBar({ groupName, gender, opponentName }) {
  return (
    <div className={styles.topBar}>
      <div className={styles.groupName}>{groupName}</div>
      <div className={styles.chatBottomBar}>
        <div className={styles.left}>
          <Link to="/chats">
            <img
              alt=""
              src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/60605814-9ca5-44e6-b700-18549a8bf841_1697439260687989733?Expires=-62135596800&Signature=CBTQwaMvZSKES8D8Zr5MYEBGYP4pFQrBe68ZDBihMsaRWbHXmJPNiuUN-r94WT9wXhTB89NbkfVvydtHrFWy80U-tRiNrVcVman5OWJkkSIzBOQLoHEh1Lee~ETslcXyxCohirUXlv-x2GqfQ6bxR2GKIej04tYJpOOq4V8koLVwc7sJSnbhGm5~wlB6PAajIsoTWcyTdo33eNjVwRMuPGk8nIh149b2gkkIJEaKRbcSVNr1BHsX4BcYjTiecUG2SYB6wtOrkv2mhOdJIxMOSUdKIL9XvNIL~6O2ftgqIkLMGCg~7~WX1zmFVkgJOM76MXrwAEAiVnNSGMwSMhiyyw__&Key-Pair-Id=K1P54FZWCHCL6J"
              className={styles.exitButton}
            />
          </Link>
          <div
            className={gender === "male" ? styles.maleDot : styles.femaleDot}
          />
        </div>

        <div className={styles.center}>
          <div className={styles.opponentName}>{opponentName}</div>
        </div>
        <div className={styles.right}>
          <div className={styles.iconGroup}>
            <img
              alt=""
              src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/0d2de4a3-3ab8-4be8-a409-918745cc0538_1697439260688299579?Expires=-62135596800&Signature=TyZPkUN2hefmsrIgWY~-hxX8aySBTcxK8uBwGUnl4~V5bch9ktxqzZyOGtrpY9bzlaBfY8eRTn126AHfyxCs9z5TnSqK3G1a17G2IbDHBjod7y-D3Urp~jQxGHVXZyjg1CQlZGbuafso0xorMuo8XdFvmh-AEoOBXqBCdOJ6RxE62OLhbFFeimhmGz-gRoycVpb62O57FHsTQQQpuVSwBI-XpigMAy-puV52yOoB~iZPKpwOr14xkG~ZieVhOsh07d-5F0UTRc-P33xqmmA1PN4XiktpqOnsdMn8CP5wnPcB4LGEkp1vQCYup7r2onTVU2ssYZt4XSW3gJjbP37xYg__&Key-Pair-Id=K1P54FZWCHCL6J"
              className={styles.heartIcon}
            />
            <img
              alt=""
              src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/369432c8-ec57-4e5e-9979-2b2d520f13ea_1697439260688393490?Expires=-62135596800&Signature=kd0Oc4C5rQjmEzs9749WqaWPpTwbXyFIreDL4N95HnWvn57nM40qSOXJZP3SfUSSAd2nm1Iu6QDqjb3lcAqONFr4Xi~1v7FORqh6jgg3Og22q5KJPiKwORxysP-~17kRTvga8ARwYjZLk~YLBlyuBvxMcIbe~n9CJyS0jV3VHjrMItu7yHxiavpzkP~HdWctZiaEY0DX4Fc4Dd89QH11QXTFfM78~bs7tFLZ9jqY9UeBVbEUqLrRewfWp6qio2otK2Av6Jx27GXgqPXAMWlQvqrokqQGUOEeWG4DVgvh2pvM-l~8AOM5i9SDIjmtQOQH9Lus-jO9o9ESPULM-KANEQ__&Key-Pair-Id=K1P54FZWCHCL6J"
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ChatDetailTopBar;
