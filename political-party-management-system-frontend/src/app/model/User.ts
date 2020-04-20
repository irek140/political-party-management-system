export interface User {
  id?: number;
  username: string;
  password: string;
  email?: string;
  name?: string;
  surname?: string;
  enabled?: boolean;
  authorities?: Authority[];
  member?: boolean;

  region?: string;
  constituency?: string;

  partyPresident?: boolean; //prezes partii
  partyVicePresident?: boolean;
  partySecretary?: boolean;

  regionPresident?: boolean; //prezes regionu
  regionVicePresident?: boolean;
  regionSecretary?: boolean;

  constituencyPresident?: boolean; //prezes okręgu
  constituencyVicePresident?: boolean;
  constituencySecretary?: boolean;

  nationalBoard?: boolean; //zarząd krajowy
  nationalCouncil?: boolean; //rada krajowa
  partyCourt?: boolean; //sąd partyjny

  sendRequest?: boolean; //czy użytkownik wysyłał prośbę o członkostwo
  banned?: boolean;
}

export interface Authority {
  id?: number;
  authority: string;
}
