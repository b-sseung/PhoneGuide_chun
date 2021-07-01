package org.techtown.guide;

import androidx.annotation.Nullable;

import org.techtown.guide.main.MainListAdapter;

public class AppConstants {
    public static String DATABASE_NAME = "guide.db";
    public static String LEVEL_CLEAR = "학습하였습니다.";
    public static String LEVEL_FAIL = "아직 배우지 않았습니다.";

    public static String touchLevel = LEVEL_FAIL;
    public static String jaumLevel = LEVEL_FAIL;
    public static String moumLevel = LEVEL_FAIL;
    public static String etdLevel = LEVEL_FAIL;
    public static String scrollLevel = LEVEL_FAIL;
    public static String typing_s_Level = LEVEL_FAIL;
    public static String typing_l_Level = LEVEL_FAIL;
    public static String typing_one_Level = LEVEL_FAIL;


    public static String touchName = "기본 터치 연습하기";
    public static String jaumName = "글자(자음) 입력 연습하기";
    public static String moumName = "글자(모음) 입력 연습하기";
    public static String etdName = "글자 이외의 키패드 배우기";
    public static String scrollName = "스크롤(상하/좌우 움직이기)";
    public static String typing_s_Name = "짧은 글 입력 연습하기";
    public static String typing_l_Name = "긴 글 입력 연습하기";
    public static String typing_one_Name = "한 단어 입력 연습하기";

    public static String[] Level = new String[]{touchLevel, scrollLevel, etdLevel, jaumLevel, moumLevel, typing_one_Level, typing_s_Level, typing_l_Level};
    public static String[] Name = new String[]{touchName, scrollName, etdName, jaumName, moumName, typing_one_Name, typing_s_Name, typing_l_Name};

    public static String[] jaums = new String[]{"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
    public static String[] moums = new String[]{"ㅏ","ㅔ","ㅓ","ㅐ","ㅗ","ㅚ","ㅜ","ㅟ","ㅡ","ㅣ",
                                                    "ㅑ","ㅒ","ㅕ","ㅖ", "ㅘ","ㅙ","ㅛ","ㅝ","ㅞ","ㅠ","ㅢ"};
    public static String[] symbols = new String[]{"[ . ]를 입력하고", "[ , ]를 입력하고", "[ ! ]를 입력하고", "[ ? ]를 입력하고",
            "입력된 것을 지우고", "[ ., ]를 입력하고", "[ ,? ]를 입력하고", "띄어쓰기를 세번 누르고"};
    public static String[] symbols_answer = new String[]{".", ",", "!", "?", "", ".,", ",?", "   "};
    public static String[] typing_ones = new String[]{"가", "헤", "이", "즐", "넛", "전", "화", "음", "료", "수", "카", "톡", "물", "이", "어",
            "폰", "에", "어", "팟", "길", "깜", "빡", "과", "자", "얼", "른", "코", "로", "나", "끝", "나", "라", "일", "본", "좀", "들", "어",
            "가", "자", "마", "스", "크", "도", "그", "만", "끼", "고", "싶", "다", "집", "가", "면", "물", "시", "켜", "야", "지", "마", "실",
            "물", "없", "으", "니", "까"};
    public static String[] typing_shorts = new String[]{"마지막", "품절", "사무", "세상", "물정", "복원", "중계", "수료", "가슴", "내막", "마음",
            "카드", "핸드폰", "안약", "종이", "용기", "공부", "기억", "고통", "슬픔", "웃음", "희망", "최고", "응원", "사랑", "실망", "심려", "입술",
            "손발", "다리", "머리", "머리카락", "눈썹", "두통", "치통", "악성", "치과", "병원", "통원", "입원", "진료", "선물", "질문", "대답", "문의",
            "기간", "우선", "긴급", "사태", "종료", "얼른", "감기", "바이러스", "깜빡", "사탕", "과자"};
    public static String[] typing_longs = new String[]{"안녕하세요.", "어떻게 지내세요?", "잘 지내고 있어요.", "만나서 반갑습니다.",
            "안녕히 계세요.", "오랜만이네요.", "수고하세요.", "오늘 하루도 힘내자!", "다시 한 번 감사드려요.", "선물 고마워요!", "질문 있어요?",
            "이건 뭐예요?", "다시 말씀해 주세요.", "이건 무슨 뜻이에요?", "이거 두 개 주세요.", "여기 다섯 명 있어요.", "삼십 분 걸려요.", "벌써 시간이 이렇게 되었네요.",
            "오래 걸리는군요. 어서 가세요.", "한 달에 얼마 정도 나와요?",
            "좋아하는 음식이 뭐예요?", "지금 그걸 보고 있어요", "그 옷은 다림질해야 해요.", "냄새가 군침 돌게 하는군요!", "실례합니다. 여기가 어디예요?",
            "집 안 환기 좀 해야겠어요."};

    public static int[] clearScroll = new int[]{0, 0, 0, 0};


    public static final int REQUEST_CODE_MAIN = 101;
    //ToastActivity
    public static final int REQUEST_CODE_TOUCH = 102;
    public static final int REQUEST_CODE_KEYPADPAGE = 103;
    public static final int REQUEST_CODE_KEYPADPAGE2 = 104;
    public static final int REQUEST_CODE_KEYPADQUIZ = 105;
    public static final int REQUEST_CODE_KEYPADQUIZ2 = 106;
    public static final int REQUEST_CODE_SCROLL = 107;
    //backbutton
    public static final int REQUEST_CODE_BACK = 108;



    //자음 관련 설명 띄울지
    public static boolean KeypadPage_ifValue = true;
    public static boolean KeypadMethod_ifValue = true;
    public static boolean KeypadMethod2_ifValue = true;
    public static boolean KeypadQuiz_ifValue = true;


    //모음 관련 설명 띄울지
    public static boolean KeypadPage2_ifValue = true;
    public static boolean KeypadPage2_ifValue2 = true;
    public static boolean KeypadQuiz2_ifValue = true;


    //자음모음 이외 관련 설명 띄울지
    public static boolean KeypadPage3_ifValue = true;
    public static boolean KeypadPage3_ifValue2 = true;
    public static boolean KeypadPage3_ifValue3 = true;
    public static boolean KeypadQuiz3_ifValue = true;

    //스크롤 설명 띄울지
    public static boolean SCROLL_ifValue = true;
    public static boolean SCROLL_ifValue2 = true;
    public static boolean SCROLL_ifValue3 = true;

    //단어 입력 연습 설명 띄울지
    public static boolean TYPING_S_ifValue = true;

    //문장 입력 연습 설명 띄울지
    public static boolean TYPING_L_ifValue = true;


    //한 글자 입력 연습 설명 띄울지
    public static boolean TYPING_O_ifValue = true;
    public static boolean TYPING_O_ifValue1 = true;
    public static boolean TYPING_O_ifValue2 = true;
    public static boolean TYPING_O_ifValue3 = true;

}


