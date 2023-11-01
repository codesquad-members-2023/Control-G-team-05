import Modal from "react-modal";
import styles from "../css/Modal.module.css";
import { CONSTANT } from "../../constants/Constant";
import { toast } from "react-toastify";

function PopupMessage({
  memberId,
  selected,
  modalState,
  setModalState,
  modalButtonClicked,
  setModalButtonClicked,
}) {
  const modalIconList =
    selected === "like"
      ? CONSTANT.PROFILE_MODAL_LIKE_ICON_LIST(memberId)
      : selected === "chat"
      ? CONSTANT.CHAT_MODAL_ICON_LIST(memberId) // "chat"을 선택했을 때의 리스트
      : CONSTANT.PROFILE_MODAL_MATCHED_ICON_LIST(memberId); // 다른 경우의 리스트

  const handleModalIconButtonClick = async (icon) => {
    await icon.apiRequest();
    setModalState(false);
    setModalButtonClicked(!modalButtonClicked);
    toast(icon.label + " 성공");
  };

  return (
    <Modal
      isOpen={modalState}
      onRequestClose={() => setModalState(false)}
      className={styles.modalContent}
      overlayClassName={styles.modalOverlay}
      ariaHideApp={false}
      shouldCloseOnOverlayClick={true}
      parentSelector={() => document.querySelector("#modalRoot")}
    >
      <div className={styles.div}>
        <div className={styles.header}>
          <div className={styles.title}>Options</div>
          <img
            className={styles.exit}
            alt=""
            src={CONSTANT.EXIT_ICON}
            onClick={() => setModalState(false)}
          />
        </div>
        <div className={styles.iconList}>
          {modalIconList.map((icon, index) => (
            <div key={index} className={styles.iconContainer}>
              <img
                className={styles.icon}
                alt=""
                src={icon.src}
                onClick={() => handleModalIconButtonClick(icon)}
              />
              <div>{icon.label}</div>
            </div>
          ))}
        </div>
      </div>
    </Modal>
  );
}

export default PopupMessage;
