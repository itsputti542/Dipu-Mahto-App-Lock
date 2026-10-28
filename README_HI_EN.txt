DIPU MAHTO APP LOCK — Android starter project
हिंदी + English

IMPORTANT:
- यह Android Studio source project है, तैयार APK नहीं।
- Android 10+ (API 29+) को लक्ष्य करता है; Android 16 और अलग-अलग ब्रांड पर वास्तविक टेस्ट जरूरी है।
- पहली बार PIN सेट करें, फिर 'Enable App Lock Service' दबाकर Android Accessibility settings में इस ऐप की सेवा स्वयं चालू करें।
- सूची में जिन ऐप्स पर लॉक चाहिए, उन्हें चुनें।
- Fingerprint/biometric तभी चलेगा जब फोन में Android BiometricPrompt-compatible biometric configured हो।
- Face unlock केवल उन डिवाइसों पर उपलब्ध होगा जहाँ Android इसे मजबूत biometric के रूप में उपलब्ध कराता है।
- Voice unlock इस starter में शामिल नहीं है; केवल आवाज की नकल से सुरक्षा कमजोर हो सकती है।
- Accessibility permission संवेदनशील अनुमति है। इसे केवल अपने फोन पर, समझकर चालू करें।
- यह शुरुआती प्रोजेक्ट है; production security, bypass handling, OEM background restrictions और Android 10–16 compatibility की जाँच बाकी है।

BUILD:
1. Android Studio में इस folder को Open करें।
2. Gradle sync पूरा होने दें।
3. फोन में Developer options और USB debugging चालू करके Run करें, या Build > Build APK(s) चुनें।
4. फोन में Accessibility settings से DIPU MAHTO APP LOCK सेवा enable करें।

App-lock व्यवहार Android version और निर्माता के अनुसार बदल सकता है। इसे सभी फोन पर काम करने की गारंटी न समझें।
