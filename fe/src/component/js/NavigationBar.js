import styles from "../css/NavigationBar.module.css";
import { Link, useLocation } from "react-router-dom";

function NavigationBar() {
  const location = useLocation();
  const linksData = [
    {
      to: "/main",
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4ad65225-c21d-4f6a-83a0-3c1a712c0db4_1697439258068069762?Expires=-62135596800&Signature=TRtRrl41Ozz4WFZwf4sv-f8aOQWcoczP9cLXx2eqTSDEwAmZhfb8umImNGEKmu-JeFDTZg5nc9HElZa2LsCIWQFaTcvTKbPuq89tGC5FxUVg26hwWCn~nYCW7UG6T6~PlbAjRBIrdwKGxyA9jyhHflEr8v9~YeH5a1~1PkP7IFVsuI25c-BwwH9p-wxAi1ZTkPCiuZDTdKCiHtF-pYrDX~dp3b-QdfhKaue-gFA8UWQP-ckHP3XGBk6mNgsN6UH7JERRw-9VJoR2BerFLHVSlFwmKF7DTOI61dWcROEdLzCxSacnMWn-dXhQ2WyvATqkWLRCECrqF4VARbFxEH9TTg__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Groups",
    },
    {
      to: "/chats",
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/6fe3ba32-6c23-4a53-ba56-435dcbf75490_1697439097328350807?Expires=-62135596800&Signature=Zv-fRH7r8Pi0a9ML~ZMozsdu0Y~uY3P1Vr-uKrNLuS0oCXAI3WmS7zKgihKpVV7XP~-Udn17dayLki3NFPo~E4Z2vibhkS0Vc6FZ-V3LrPQ-rQXsVAK--pwUEWgVuktEfp02Wgn9PPP6zxUjDjJJNAh7qrfJFfl9GtbUFcm9mzjpexVmy44hUXxS-ZVLklynG8nbikLS5irjUhGuVHX~FIfFK6oVvovxLqgtbf4Bt~ek2NrIz~Yaypz1upj2y1stQxIYQmbuzOM7IO8H3NmS1kUuxKmR4H6uo4~IRZi5Jtf~MnW7NY4rlvVPX5DFFZvAkVrB45R4IeTBfmlVHchXBg__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Chats",
    },
    {
      to: "/groups",
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/75733f17-06f6-4ece-8cce-bebb0dcd28b1_1697439097328276180?Expires=-62135596800&Signature=fYlbL3MRkyaD871lRUkfpLsxpvm7q8GsGtqsfewrMhelpc0S40TkD2EPNahN2wDYJJeKUPpv-OlH0u-BTDFCaWEYuYxUm8yDR1QF0cWxq~jZfKZdSVAUglBZqslwhMJ5e6LLChWuYwWCRT4SgOd529rImncQW9RMUcVNVM~sJZzVfkudgaVokqqZsWP0OrmvYl8ocOoHGzv7f3k9KmC7gayK~0KmGC5bmPBwvs5fUfvoD5ZY~MgSzXDn6HneFWyNYoocEU9nMhn1ytQC6ZtITLhBlZb3C6z8JXml1inuLgknRdo-2VPYSXTqEPaZNpoMZg6Hk51uMBQZFhMs-ohm1w__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Search",
    },
    {
      to: "/members",
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/ea6f5990-c61b-463e-a7a6-972cd3cfd34c_1697439097328419467?Expires=-62135596800&Signature=A16x1~EU3PuOXKj7bHbJy3ZoqP~lV6lpbHYDrsmQw5L1MpEz~ekxpDd3k1PCeVBGsT9RWuztUryo2Q6otvV2EigPZ9e3tY1UGlMLP~lYvoD~2~x8CDXo5KeUwkyf5zB7ioChSKljpbHBGa~GEamjUtjXLp8nSU0UzSawWyZ-yA9NeDmgmFerks5POh7cN2G~y01~stbciNDauvh0dAnBHDyw2hQxZVs3qwdtY8Zs8LLIp5xvvTsNPnRIfoM~yxPQ87Eta6F4KjBFxnkdH42EVQEvVC1Lb~y2fVWn~VO95bDdg2wnvblB3yDt1dqi345Gz-SYeKzFml08KLr4QfAvQw__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "User",
    },
  ];

  return (
    <div className={styles.bottomBar}>
      {linksData.map((link) =>
        (() => {
          const isActive = location.pathname === link.to;
          const activeStyle = isActive ? styles.active : "";

          return (
            <Link
              key={link.to}
              className={`${styles.link} ${activeStyle}`}
              to={link.to}
            >
              <img
                className={`${styles.supervisorAccountIcon} ${activeStyle}`}
                alt=""
                src={link.src}
              />
              <span className={`${styles.groups} ${activeStyle}`}>
                {link.label}
              </span>
            </Link>
          );
        })()
      )}
    </div>
  );
}

export default NavigationBar;
