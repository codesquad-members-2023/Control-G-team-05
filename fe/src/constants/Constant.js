import { fetchMemberBlock } from "../api/member/MemberBlock";
import { fetchMemberLike } from "../api/member/MemberLike";

export const CONSTANT = {
  PROFILE_MODAL_LIKE_ICON_LIST: (memberId) => [
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e60fd142-644e-4ba6-90d1-446d75989bb6_1697725729806953934?Expires=-62135596800&Signature=gmQHe7zUDxu6Fg9OF9wV46fMglzCMtrIZ7c2bU~cFqN1W2SLhgQFJEK8OOaT-obuGMhoFm-8NCePic0wYk8bWPwzcIlasng249yKHzCwDtlXM7rPuRsfEsAOfRZkbqNUv1IOotfdXFh96zULIJLNuxNRFfuMVj6ZuCRgqA0apY68qVpj4-RAMbByUKU4kQhPrR7jhzvA0RyznoBt7RIRXYMbJlu5YIAYVcoXAjwyG5N2cfvhmxt5kBT8dhu~bL7Jat7VZWayBtKGYIEizf8WNX0n4N2MmC2S5ZhX9Km9burY9J1MBkakqbqZsviY7dZ7OK~qObOdtpwPlv4OyZDUtg__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Block",
      apiRequest: () => fetchMemberBlock(memberId),
    },
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/f94bedc1-f531-4e16-a4b4-2307d4c73689_1697725729807050738?Expires=-62135596800&Signature=HcIvFWAsoQcdBr2sHCSY0BwVA~3bo~JsUppvKhEic~6YWC4aL9zN111uSnxpEcWwn4n-rgeoiUUGD0asgiS8mUYvcrfNY9SOAcfKmlhsNVvU0fxCbD8zNx75LIVfjzPYm2BvSJP8CZ2Fo77zE2NsaDGZ8C8GbyoEZqY80QG-X52RyPkNDwqfeWtJcI2S6Hso3~yJ18doLyCgx~xXSW7BFoPDJPepdUFexsLZCYQxpFGP9NVyyGUVvSVR-WI9jc9t~sz9tUUahIATCo9uFBKnmRh-I4OKk14qz03VSEl3Lypx-Q8rPxqDqI0HM--VV901kleWm13BmhaZ~82qtxb1PA__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Unlike",
      apiRequest: () => fetchMemberLike(memberId),
    },
  ],
  PROFILE_MODAL_MATCHED_ICON_LIST: (memberId) => [
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/6cfc43fe-f6a8-4a9d-a4fc-5000be8f8fcf_1698288243557872787?Expires=-62135596800&Signature=g-ZMEppOsXis0i7Gr~AilZa6q~ZLKIUEzL4MvyqhQEOeLoyEWJIVITtEatk7ZaGIzHWLK5hUzYAumjq1F--Znri7HeUPz4D9EcBWWuBDBzB3qo0SVJ8nBIBZhp7GKvdMsbGfDJcFqojHm-HFqwUK-OusOtM935h-K4uRBOOSWFmdIbnR6NALoaLus4eZhKICvnvzKQWvR3YFkxyq9nRBKS5JoVPcc4x8xLXyfeEu8FFV98esHcdAlSBAHqbKtZNVMMMqK2-okfeuq4GvjnxvkaNroT0Vgv36ssaJI7X-e7aTDfqx6iDAyFTzJ-H1MCmOfZ~79sTbqtpd6NahXPz9xQ__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Chat",
      apiRequest: "block",
    },
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e60fd142-644e-4ba6-90d1-446d75989bb6_1697725729806953934?Expires=-62135596800&Signature=gmQHe7zUDxu6Fg9OF9wV46fMglzCMtrIZ7c2bU~cFqN1W2SLhgQFJEK8OOaT-obuGMhoFm-8NCePic0wYk8bWPwzcIlasng249yKHzCwDtlXM7rPuRsfEsAOfRZkbqNUv1IOotfdXFh96zULIJLNuxNRFfuMVj6ZuCRgqA0apY68qVpj4-RAMbByUKU4kQhPrR7jhzvA0RyznoBt7RIRXYMbJlu5YIAYVcoXAjwyG5N2cfvhmxt5kBT8dhu~bL7Jat7VZWayBtKGYIEizf8WNX0n4N2MmC2S5ZhX9Km9burY9J1MBkakqbqZsviY7dZ7OK~qObOdtpwPlv4OyZDUtg__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Block",
      apiRequest: () => fetchMemberBlock(memberId),
    },
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/f94bedc1-f531-4e16-a4b4-2307d4c73689_1697725729807050738?Expires=-62135596800&Signature=HcIvFWAsoQcdBr2sHCSY0BwVA~3bo~JsUppvKhEic~6YWC4aL9zN111uSnxpEcWwn4n-rgeoiUUGD0asgiS8mUYvcrfNY9SOAcfKmlhsNVvU0fxCbD8zNx75LIVfjzPYm2BvSJP8CZ2Fo77zE2NsaDGZ8C8GbyoEZqY80QG-X52RyPkNDwqfeWtJcI2S6Hso3~yJ18doLyCgx~xXSW7BFoPDJPepdUFexsLZCYQxpFGP9NVyyGUVvSVR-WI9jc9t~sz9tUUahIATCo9uFBKnmRh-I4OKk14qz03VSEl3Lypx-Q8rPxqDqI0HM--VV901kleWm13BmhaZ~82qtxb1PA__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Unlike",
      apiRequest: () => fetchMemberLike(memberId),
    },
  ],
  CHAT_MODAL_ICON_LIST: (memberId) => [
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/6cfc43fe-f6a8-4a9d-a4fc-5000be8f8fcf_1698288243557872787?Expires=-62135596800&Signature=g-ZMEppOsXis0i7Gr~AilZa6q~ZLKIUEzL4MvyqhQEOeLoyEWJIVITtEatk7ZaGIzHWLK5hUzYAumjq1F--Znri7HeUPz4D9EcBWWuBDBzB3qo0SVJ8nBIBZhp7GKvdMsbGfDJcFqojHm-HFqwUK-OusOtM935h-K4uRBOOSWFmdIbnR6NALoaLus4eZhKICvnvzKQWvR3YFkxyq9nRBKS5JoVPcc4x8xLXyfeEu8FFV98esHcdAlSBAHqbKtZNVMMMqK2-okfeuq4GvjnxvkaNroT0Vgv36ssaJI7X-e7aTDfqx6iDAyFTzJ-H1MCmOfZ~79sTbqtpd6NahXPz9xQ__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Exit",
      apiRequest: "block",
    },
    {
      src: "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e60fd142-644e-4ba6-90d1-446d75989bb6_1697725729806953934?Expires=-62135596800&Signature=gmQHe7zUDxu6Fg9OF9wV46fMglzCMtrIZ7c2bU~cFqN1W2SLhgQFJEK8OOaT-obuGMhoFm-8NCePic0wYk8bWPwzcIlasng249yKHzCwDtlXM7rPuRsfEsAOfRZkbqNUv1IOotfdXFh96zULIJLNuxNRFfuMVj6ZuCRgqA0apY68qVpj4-RAMbByUKU4kQhPrR7jhzvA0RyznoBt7RIRXYMbJlu5YIAYVcoXAjwyG5N2cfvhmxt5kBT8dhu~bL7Jat7VZWayBtKGYIEizf8WNX0n4N2MmC2S5ZhX9Km9burY9J1MBkakqbqZsviY7dZ7OK~qObOdtpwPlv4OyZDUtg__&Key-Pair-Id=K1P54FZWCHCL6J",
      label: "Block",
      apiRequest: () => fetchMemberBlock(memberId),
    },
  ],
  PROFILE_MODAL_BUTTON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/982ed9a1-ac0e-49e8-b5ce-db1f4f82875a_1697439263404503464?Expires=-62135596800&Signature=wByhy~9rSG41pl5oYzKHPPVri-8uAXuGyroGk3uKK2zktumIECl2yXMkIDMBBEFajk~OqpRFJzRyQSY2~1jXWROf9SfEfYb68E4tld7n8reu8al3aQevAVIipiFojMIA0WU5kLL4AqPi~7NGB9QiT95qD54F6ynU3tvc3e6SNQelOxDviQ0RH2iuQxEknOFlQTzdjEcUnt49ip1T4pI62L-tSX29ePusgpCf4IfU9AQ8GdIoCSJAknHB7f5LGQBYKN8orRonqyH1U7TccDoIOkUAKl22GMbvOyFEXGfqGCG6XwG~bNChUIVw3lUnu9Op-4VUDvapwIVUgMFWlbJYvQ__&Key-Pair-Id=K1P54FZWCHCL6J",
  PROFILE_EDIT_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3fd74c1c-5c2e-43fe-8fa5-bd8a70c164da_1697439263404720240?Expires=-62135596800&Signature=GHYl4ivleK~vg7fT0zukZvm1WlpenhPw0LCQXydo-PhJiqoMzhBv~4QQx0T77ROnJTcLHg2UO8UipfwFz3XDaj3AcjRwd97yXnRcvaWhoC8mv3B6TEGl9l3o03Dzvotg20ayxvt95u6JO4sLVkuTTRONQeO507jA~8ph7O0z8NOzPFvnvbm9NriZxypbI4eg96nlmdy0kQnl4vJuZ4V2~pmNzMlku6iIta3~M~6Nw3Zn8sASky9bJ1MXK8IC2IrP-GkdUsc3iNd67upuVmQP-IMA4oNF4Bdx2Fkw2XPW4-GJBP7cVU6ij0JDYZeZD-x-vWtA4lvklVYKBAuHkUrFvg__&Key-Pair-Id=K1P54FZWCHCL6J",
  EXIT_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/065fbf83-4be7-4e92-bde8-31bc99cc9015_1698288243557675733?Expires=-62135596800&Signature=e5lemh7HOwq6iVRuVp6L5AQsfYPHrf1PjjBxHbdgStNq0nZqDgik4NqjOZ~qJ5YY6~VW0qv9BJdoq0h9S0GbhELenm8N4kj1d0qcuPBLCBkYu8jn~9as1QKlkd4fU~FeIwGwOUtOKT~nYvCJZfMvXIX9Vt061NRncoZ5GSbegpuPtDfpPSA-vDNoj6JFmptfS~dd7OEMDxu7-VQ-ES3IVwJoYofXHD1UF46iPzVjgu0wyPdxoz0g4OLxz~DTRAb~X3mdXGHc6pyiLu~hmWU8Sf2X537kXI5Sh3RXQjB5VSqcKECZ67FC8s65YO4YO5A~aiOF3uWwn-HslTBtt8IDpQ__&Key-Pair-Id=K1P54FZWCHCL6J",
  IMG_UPLOAD_ICON:
    "data:image/png;base64,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",
  CONTROL_G_ICON:
    "https://www.notion.so/image/https%3A%2F%2Fcdn-icons-png.flaticon.com%2F512%2F10074%2F10074958.png?table=block&id=77c107d2-ff9c-49d3-bc7d-da98a81bc80c&spaceId=9987bb01-df95-42d3-8249-04050ff2ede3&width=250&userId=59ce483d-1566-4ee4-a097-ca0972c7e50a&cache=v2",
  GROUP_ADD_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3173bc89-f47f-4157-a4f9-de5d512156af_1697439276210455055?Expires=-62135596800&Signature=DhFgbo~gFIC93sasVDH8FdTwIwVMw3xKz8vjdQ9lz4f1FmIV4gsblUugO6be3yn7-pTOesLIlS36VXoEyTY2G270WlJfq-j7~rKYcabQUx8prWN9PepZbAiZT~~mkgq3TxaoiW8SOq9tg1tc4R64TTRFlYzg3YZFdJ8kK25eIQb5rTrh0Tj9OQBckt-6ehW0JLGqabWqv2WL9fGcoI7KRLFpXTEgUUcJpJuZXGLPRvicToPyoXb66BUfcrE-iYmpmAQH7sDZX42iZvVk3s14ffKMaCPy~bO6vPy1aGe1LZSEfl6a96tbjXYuRBVTVhfqBTMdgJK46nc5FJG9qT6VwQ__&Key-Pair-Id=K1P54FZWCHCL6J",
  RANDOM_CHAT_START_BUTTON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4fbb8d44-8109-413f-b5c6-ac332ef15dd3_1697593141402360165?Expires=-62135596800&Signature=K8jngQR0vyPUOVpFBr6ca3jr9VPzcU2Fog2EbqUf2abHNNI-eihJjhYoA8r9VInImjlBBadGPOYFWxJYeF3pUA3lWIik~gpvGHJkk0IAbr~AbfG-3eXYk9FttV3UHCoPU-uZk1weXA4~YWgYVjfSAhyEvub~rsqo9~OQnAhnwOcpIsejATqOkk~SRiHsUNl54EYwutJ2Btax5MdgJMXG6509cs0oo0AziNAWNXrWW4WlFL0RGpKlEwRKu3MqE7USoZbvcFK~rhRVdyp6xzwNGvO0eFqOsbsb5DVSz7~xUXCJQ4eC4epXOI1O0WERLn0Ic-SvwyWLy6oYWb-wq9BhGg__&Key-Pair-Id=K1P54FZWCHCL6J",
  HEART_CON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/a466ca8f-750b-4ed5-ba53-9460f8eb7465_1697439263403882824?Expires=-62135596800&Signature=IquFwWmu6JvIlfjU91ht3orqsLjtM2MdUxSwyA2fzTSAmsN0gJYiSZiT29xiDmSxy6QBAA0PafMkiagZ~oGqhLYvct2wBjFKelutYVGpuPWaSuriZlSWU3dgohvHMzsQED43zY5nlSTB6DH1cocEAq71HIQT128ZxtpRPGqiqAXiqkHkNq3gXJyCXZFfXyHkJC-huq0HlyP0Ur7le9y3IeJ1vS~ofGwHQgwnRlwDf2Gb2otHptT3~Lp4xxrazY1SZjiY3R0IAwKGDLy5vb1JfAuuxakI-cKdyY88u24vLucK9fUAX-BJ6zEy4SHdJpIKGZdMtKLdCZ-sbicblB0PtQ__&Key-Pair-Id=K1P54FZWCHCL6J",
  NAVIGATION_BAR_GROUP_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/4ad65225-c21d-4f6a-83a0-3c1a712c0db4_1697439258068069762?Expires=-62135596800&Signature=TRtRrl41Ozz4WFZwf4sv-f8aOQWcoczP9cLXx2eqTSDEwAmZhfb8umImNGEKmu-JeFDTZg5nc9HElZa2LsCIWQFaTcvTKbPuq89tGC5FxUVg26hwWCn~nYCW7UG6T6~PlbAjRBIrdwKGxyA9jyhHflEr8v9~YeH5a1~1PkP7IFVsuI25c-BwwH9p-wxAi1ZTkPCiuZDTdKCiHtF-pYrDX~dp3b-QdfhKaue-gFA8UWQP-ckHP3XGBk6mNgsN6UH7JERRw-9VJoR2BerFLHVSlFwmKF7DTOI61dWcROEdLzCxSacnMWn-dXhQ2WyvATqkWLRCECrqF4VARbFxEH9TTg__&Key-Pair-Id=K1P54FZWCHCL6J",
  NAVIGATION_BAR_CHAT_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/6fe3ba32-6c23-4a53-ba56-435dcbf75490_1697439097328350807?Expires=-62135596800&Signature=Zv-fRH7r8Pi0a9ML~ZMozsdu0Y~uY3P1Vr-uKrNLuS0oCXAI3WmS7zKgihKpVV7XP~-Udn17dayLki3NFPo~E4Z2vibhkS0Vc6FZ-V3LrPQ-rQXsVAK--pwUEWgVuktEfp02Wgn9PPP6zxUjDjJJNAh7qrfJFfl9GtbUFcm9mzjpexVmy44hUXxS-ZVLklynG8nbikLS5irjUhGuVHX~FIfFK6oVvovxLqgtbf4Bt~ek2NrIz~Yaypz1upj2y1stQxIYQmbuzOM7IO8H3NmS1kUuxKmR4H6uo4~IRZi5Jtf~MnW7NY4rlvVPX5DFFZvAkVrB45R4IeTBfmlVHchXBg__&Key-Pair-Id=K1P54FZWCHCL6J",
  NAVIGATION_BAR_SEARCH_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/75733f17-06f6-4ece-8cce-bebb0dcd28b1_1697439097328276180?Expires=-62135596800&Signature=fYlbL3MRkyaD871lRUkfpLsxpvm7q8GsGtqsfewrMhelpc0S40TkD2EPNahN2wDYJJeKUPpv-OlH0u-BTDFCaWEYuYxUm8yDR1QF0cWxq~jZfKZdSVAUglBZqslwhMJ5e6LLChWuYwWCRT4SgOd529rImncQW9RMUcVNVM~sJZzVfkudgaVokqqZsWP0OrmvYl8ocOoHGzv7f3k9KmC7gayK~0KmGC5bmPBwvs5fUfvoD5ZY~MgSzXDn6HneFWyNYoocEU9nMhn1ytQC6ZtITLhBlZb3C6z8JXml1inuLgknRdo-2VPYSXTqEPaZNpoMZg6Hk51uMBQZFhMs-ohm1w__&Key-Pair-Id=K1P54FZWCHCL6J",
  NAVIGATION_BAR_USER_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/ea6f5990-c61b-463e-a7a6-972cd3cfd34c_1697439097328419467?Expires=-62135596800&Signature=A16x1~EU3PuOXKj7bHbJy3ZoqP~lV6lpbHYDrsmQw5L1MpEz~ekxpDd3k1PCeVBGsT9RWuztUryo2Q6otvV2EigPZ9e3tY1UGlMLP~lYvoD~2~x8CDXo5KeUwkyf5zB7ioChSKljpbHBGa~GEamjUtjXLp8nSU0UzSawWyZ-yA9NeDmgmFerks5POh7cN2G~y01~stbciNDauvh0dAnBHDyw2hQxZVs3qwdtY8Zs8LLIp5xvvTsNPnRIfoM~yxPQ87Eta6F4KjBFxnkdH42EVQEvVC1Lb~y2fVWn~VO95bDdg2wnvblB3yDt1dqi345Gz-SYeKzFml08KLr4QfAvQw__&Key-Pair-Id=K1P54FZWCHCL6J",
  CHATLIST_NOTIFICATION_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/f3da5d67-24d5-4ef7-82ca-75525805fc3a_1697439258068277195?Expires=-62135596800&Signature=RFFKtdnjAeY5--u9G0WB3Zz4OBH77IfbjtFlgYdZNlImwd6Ycl2R5wyg62~Nj36vQPD-rBXfsoyKlprNa~Yb70CdPsX4DQSpJnCLPhcnh30Xu5JsVt8czvIWUL9tXa50Q8sMX97AN1VNmsb1IebR9djVIodjcugpoOnxy7a2kJPjJazhKkYI9w2szkydkkWZ06V2HlcYbaTpTkjEw8fkWF6ZORPsfInFUzIZfHKqbneSqx5EI9SBmr0niHtBMWsDe0S2I3t9mjo9R1WD0XyLCHEHUFSU32ETxSBknCho6hsp0vKwa3oAcfmzCueNwH0l1AVZIkiGEzqxgKuIYpyQ7A__&Key-Pair-Id=K1P54FZWCHCL6J",
  BASIC_CHAT_PROFILE_ICON:
    "https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/3e8070f1-02fd-4d00-9ce5-8e71a52cd40f_1697439263404249404?Expires=-62135596800&Signature=pkJx9aVyJWqoMbdeDzJ2wDSIYgYSuSAXz6Y3EAe3oLdkdWAI-Ej6ioEVHNBskhVhHu2knF5lFAczVJAdkhpJtwX~PR6mHji32RnqLYT7CcW7hqp~SGKz9y6NUdSXQcwETGpgVfGvUTTx2P1WHPldFnH01MbN9b~9WXgAvsA449Z0zdKRtbXA7ZARIPhMX0OQUYUb-2EWWu4OtuCYzPMSB0gmG2lu4o6yP56Fa~9zLPPyLwI8yM-6QX6uhBCuVD1Hrf5dNhlxEudaQUcXPzfHVq9uFpkdM2PTf9MJxVPrLKblk4ZhUkw30GAABYA0-SPzVDWB~nt2szVoLystBMIeGw__&Key-Pair-Id=K1P54FZWCHCL6J",
};
