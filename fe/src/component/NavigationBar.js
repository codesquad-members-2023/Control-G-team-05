import styles from "./NavigationBar.module.css";

function NavigationBar() {
  return (
    <div className={styles.bottomBar}>
      <div className={styles.wrapper}>
        <div className={styles.menu}>
          <b className={styles.groups}>Groups</b>
          <img
            className={styles.supervisorAccountIcon}
            alt=""
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/b31bbc27-9f9d-49ca-abfa-400489676f8f_1697439097328198486?Expires=-62135596800&Signature=wBkUIg7aL8EbRKz5BLl83eGdJAVieOdN2idF8fmfvOw-g2OvFLIkY9ujxJ3ZsMOYSSSF0~8jve59yxF1nLRT6S2Lmpgn5l7rHb3Ef9lg~v4LTUIOLLQnPTcx2IuzPM55V7WSsw~l5rNL1loyXD3qfuSb27n1e--LFvpqyte5~v4zbH8L6ee~23KEsKdKPVWthraim2ZNLHCoNv6Hi9l1Rxr~n0GpujSPysowfPpWtPpwlBA889hApj8hNAyrZkORRWLFkW~05bYDdRUiBgv0HnXLC6Ru4yV87J7h-9q9IhEL9RXepLuRuur4-4nggHcLT6xF4LRZC3FNcGOMRKXhPw__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
        </div>
        <div className={styles.menu1}>
          <b className={styles.search}>Search</b>
          <img
            className={styles.supervisorAccountIcon}
            alt=""
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/75733f17-06f6-4ece-8cce-bebb0dcd28b1_1697439097328276180?Expires=-62135596800&Signature=fYlbL3MRkyaD871lRUkfpLsxpvm7q8GsGtqsfewrMhelpc0S40TkD2EPNahN2wDYJJeKUPpv-OlH0u-BTDFCaWEYuYxUm8yDR1QF0cWxq~jZfKZdSVAUglBZqslwhMJ5e6LLChWuYwWCRT4SgOd529rImncQW9RMUcVNVM~sJZzVfkudgaVokqqZsWP0OrmvYl8ocOoHGzv7f3k9KmC7gayK~0KmGC5bmPBwvs5fUfvoD5ZY~MgSzXDn6HneFWyNYoocEU9nMhn1ytQC6ZtITLhBlZb3C6z8JXml1inuLgknRdo-2VPYSXTqEPaZNpoMZg6Hk51uMBQZFhMs-ohm1w__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
        </div>
        <div className={styles.menu2}>
          <b className={styles.chats}>Chats</b>
          <img
            className={styles.chatIcon}
            alt=""
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/6fe3ba32-6c23-4a53-ba56-435dcbf75490_1697439097328350807?Expires=-62135596800&Signature=Zv-fRH7r8Pi0a9ML~ZMozsdu0Y~uY3P1Vr-uKrNLuS0oCXAI3WmS7zKgihKpVV7XP~-Udn17dayLki3NFPo~E4Z2vibhkS0Vc6FZ-V3LrPQ-rQXsVAK--pwUEWgVuktEfp02Wgn9PPP6zxUjDjJJNAh7qrfJFfl9GtbUFcm9mzjpexVmy44hUXxS-ZVLklynG8nbikLS5irjUhGuVHX~FIfFK6oVvovxLqgtbf4Bt~ek2NrIz~Yaypz1upj2y1stQxIYQmbuzOM7IO8H3NmS1kUuxKmR4H6uo4~IRZi5Jtf~MnW7NY4rlvVPX5DFFZvAkVrB45R4IeTBfmlVHchXBg__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
        </div>
        <div className={styles.menu3}>
          <img
            className={styles.supervisorAccountIcon}
            alt=""
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/ea6f5990-c61b-463e-a7a6-972cd3cfd34c_1697439097328419467?Expires=-62135596800&Signature=A16x1~EU3PuOXKj7bHbJy3ZoqP~lV6lpbHYDrsmQw5L1MpEz~ekxpDd3k1PCeVBGsT9RWuztUryo2Q6otvV2EigPZ9e3tY1UGlMLP~lYvoD~2~x8CDXo5KeUwkyf5zB7ioChSKljpbHBGa~GEamjUtjXLp8nSU0UzSawWyZ-yA9NeDmgmFerks5POh7cN2G~y01~stbciNDauvh0dAnBHDyw2hQxZVs3qwdtY8Zs8LLIp5xvvTsNPnRIfoM~yxPQ87Eta6F4KjBFxnkdH42EVQEvVC1Lb~y2fVWn~VO95bDdg2wnvblB3yDt1dqi345Gz-SYeKzFml08KLr4QfAvQw__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
          <b className={styles.user}>User</b>
        </div>
      </div>
    </div>
  );
}

export default NavigationBar;
