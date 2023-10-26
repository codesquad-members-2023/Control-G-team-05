import styles from "../css/NavigationBar.module.css";
import { Link, useLocation } from "react-router-dom";
import { CONSTANT } from "../../constants/Constant";

function NavigationBar() {
  const location = useLocation();
  const linksData = [
    {
      to: "/main",
      src: CONSTANT.NAVIGATION_BAR_GROUP_ICON,
      label: "Groups",
    },
    {
      to: "/chats",
      src: CONSTANT.NAVIGATION_BAR_CHAT_ICON,
      label: "Chats",
    },
    {
      to: "/groups",
      src: CONSTANT.NAVIGATION_BAR_SEARCH_ICON,
      label: "Search",
    },
    {
      to: "/members",
      src: CONSTANT.NAVIGATION_BAR_USER_ICON,
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
