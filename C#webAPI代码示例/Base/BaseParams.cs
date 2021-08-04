﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Denglu1_demo.Base
{
    public class BaseParams
    {
        public const string DEFAULT_AES_KEY = "qaSaZCmm/+xA9UMViVN4tGvRNfYIU978j8NfnjSgj00=";
        public const string AESIV_String = "1234567890ABCDEF0123456789ABCDEF";
        public const string RSAPublicKey2048_xml = "<RSAKeyValue><Modulus>vJ17KMav2a3EUDJR11THuu7BN8GLC6/ymuY2huUck4DVQyHmnpqFoWf6J7aLEZpHj+dDW7iOiJcAz9mYrECqfi83AiYXEU96g3N63L0vrz6pL5fIh0AU6D69l8aoNQ6H51OGiQyYWtbMiQpxXHYkJzMdlPclm9c2tntnp4Pd8ZsbtZJSGOf3U9pTEZRX6OnFO3l1PlUHVAElgx1Lw0iZHB7vnc9c5T52nnE3gZwuo5oNJ3LpwlT8McSzOFBfPWjYXSK5QBpWUIKOs3x4cMaHAUCRidYL+8hF0Dva7P13lONlO72PrIhH2EUWdbqtElzdV0+gjeAmNqDnY9OMxhk+Qw==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        public const string RSAPrivateKey2048_xml = "<RSAKeyValue><Modulus>vJ17KMav2a3EUDJR11THuu7BN8GLC6/ymuY2huUck4DVQyHmnpqFoWf6J7aLEZpHj+dDW7iOiJcAz9mYrECqfi83AiYXEU96g3N63L0vrz6pL5fIh0AU6D69l8aoNQ6H51OGiQyYWtbMiQpxXHYkJzMdlPclm9c2tntnp4Pd8ZsbtZJSGOf3U9pTEZRX6OnFO3l1PlUHVAElgx1Lw0iZHB7vnc9c5T52nnE3gZwuo5oNJ3LpwlT8McSzOFBfPWjYXSK5QBpWUIKOs3x4cMaHAUCRidYL+8hF0Dva7P13lONlO72PrIhH2EUWdbqtElzdV0+gjeAmNqDnY9OMxhk+Qw==</Modulus><Exponent>AQAB</Exponent><P>59d+x7fm1KZr2cRRgjmeU7HkPZrB3AAeOAkmmccTRgUO+luCZ9Fc3QdqKdoef6/fwzSj7gOKWl8hu5ou2Bf7vve5eyliemIu/y4qqaVKWFED3bmi9HZcvPRIwUiZHsb3YR5jmHyrS28yjbcY5dVVn2pMmnzZDkFQhCX3vxW82LE=</P><Q>0ETkeEBdDpNqwYKASFVWlha/xBtCYcBtymCcg6+G/1DMwUwiBuD3JMKFRylZIY0iPvrazy1BayPIrKM9OHo7IjxNRS/ZXdis3RbbiuqbcMTMokGEQU2lGOnev3798J2R8CyDVAZQUZJ0sMdVk7PmiwNnXyq+B5iEw9QcFaXwAzM=</Q><DP>kIGYS0IMRcWJec3NBD+HuSErY16fsNH+A+1oOveDwuQKRvBc4vrzR+2ENZv34Jyt+01HVGauiM+9BeL/ib/BMXHVEbhk4mVqZ7HN87nMEipFH6HCuV9BRKF668alcjKQJN+9MmGan0UrHHvgr/j+CoRGRRDGdgUt+4By4I7bC7E=</DP><DQ>QR/ZxVRzBNTKBBqXnIa63ZHdgyf2+PQgILFXH8JqVznf3O2+kiIUhfUhkd/3FAu60rDEbRY8p5D39ivRP/DQvuLWUrUkpjjTzQqRuf2kf326c15DSdxKrOCsm3rNU0kiUlTI+CRFR0yhmomqU9ZkV8HHuDV4WI2zrjyMT6SkyYs=</DQ><InverseQ>UkURhXqUKcu8S/AwQFFpPAyoD7guaDejX29NT+65lBjVcfqZPGzPDTJf73l+M3rnC6n2bpOvb/YWMFaRUWruoTpvgiyKzPGGP6ixCtaS5BJ9+j702Lywlzu13EVFC8KzzRI97ecxBYEKPdmWxnvetVLAgYCTjFoIakBW9wimMhk=</InverseQ><D>kwLIANNEMw1eiwd9hLGe75OyZAvlnZlpJdSzwVaIfGuZTbb5srtL1q9damC8al7u0jn15WKnLc1Mo7S9a90HRkz0uicIKcTh/90dcTnAFf5oDiFm+gw3f4GPcuIzLw+BXAtdPCrmAT+UsmfCTcgDi84qi3ZLCPmounjbJNUCQ5JD8ByhK3HX/SLSuSYE2I2iEhJO93vIcbeGQPdqSPhDca/lq0Rw3pyhrRTtqlpBICr3uaXGqzTgu06HzJ/wtTy/oXoc+Y2HhsakIrbpVzi0rvJtrOGXNGflzzCPxsDYCn/bNQeGqxbp/VeQAj+Rl5s1+CEK6EkgiMNpIBp/jxhFYQ==</D></RSAKeyValue>";


        //UPload the RSAPublicKey2048 string to the Denglu1 Server.
        public const string RSAPublicKey2048 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvJ17KMav2a3EUDJR11THuu7BN8GLC6/ymuY2huUck4DVQyHmnpqFoWf6J7aLEZpHj+dDW7iOiJcAz9mYrECqfi83AiYXEU96g3N63L0vrz6pL5fIh0AU6D69l8aoNQ6H51OGiQyYWtbMiQpxXHYkJzMdlPclm9c2tntnp4Pd8ZsbtZJSGOf3U9pTEZRX6OnFO3l1PlUHVAElgx1Lw0iZHB7vnc9c5T52nnE3gZwuo5oNJ3LpwlT8McSzOFBfPWjYXSK5QBpWUIKOs3x4cMaHAUCRidYL+8hF0Dva7P13lONlO72PrIhH2EUWdbqtElzdV0+gjeAmNqDnY9OMxhk+QwIDAQAB";
        //Keep the RSAPrivateKey2048 string on your own Server secretly.
        public const string RSAPrivateKey2048 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC8nXsoxq/ZrcRQMlHXVMe67sE3wYsLr/Ka5jaG5RyTgNVDIeaemoWhZ/ontosRmkeP50NbuI6IlwDP2ZisQKp+LzcCJhcRT3qDc3rcvS+vPqkvl8iHQBToPr2Xxqg1DofnU4aJDJha1syJCnFcdiQnMx2U9yWb1za2e2eng93xmxu1klIY5/dT2lMRlFfo6cU7eXU+VQdUASWDHUvDSJkcHu+dz1zlPnaecTeBnC6jmg0ncunCVPwxxLM4UF89aNhdIrlAGlZQgo6zfHhwxocBQJGJ1gv7yEXQO9rs/XeU42U7vY+siEfYRRZ1uq0SXN1XT6CN4CY2oOdj04zGGT5DAgMBAAECggEBAJMCyADTRDMNXosHfYSxnu+TsmQL5Z2ZaSXUs8FWiHxrmU22+bK7S9avXWpgvGpe7tI59eVipy3NTKO0vWvdB0ZM9LonCCnE4f/dHXE5wBX+aA4hZvoMN3+Bj3LiMy8PgVwLXTwq5gE/lLJnwk3IA4vOKot2Swj5qLp42yTVAkOSQ/AcoStx1/0i0rkmBNiNohISTvd7yHG3hkD3akj4Q3Gv5atEcN6coa0U7apaQSAq97mlxqs04LtOh8yf8LU8v6F6HPmNh4bGpCK26Vc4tK7ybazhlzRn5c8wj8bA2Ap/2zUHhqsW6f1XkAI/kZebNfghCuhJIIjDaSAaf48YRWECgYEA59d+x7fm1KZr2cRRgjmeU7HkPZrB3AAeOAkmmccTRgUO+luCZ9Fc3QdqKdoef6/fwzSj7gOKWl8hu5ou2Bf7vve5eyliemIu/y4qqaVKWFED3bmi9HZcvPRIwUiZHsb3YR5jmHyrS28yjbcY5dVVn2pMmnzZDkFQhCX3vxW82LECgYEA0ETkeEBdDpNqwYKASFVWlha/xBtCYcBtymCcg6+G/1DMwUwiBuD3JMKFRylZIY0iPvrazy1BayPIrKM9OHo7IjxNRS/ZXdis3RbbiuqbcMTMokGEQU2lGOnev3798J2R8CyDVAZQUZJ0sMdVk7PmiwNnXyq+B5iEw9QcFaXwAzMCgYEAkIGYS0IMRcWJec3NBD+HuSErY16fsNH+A+1oOveDwuQKRvBc4vrzR+2ENZv34Jyt+01HVGauiM+9BeL/ib/BMXHVEbhk4mVqZ7HN87nMEipFH6HCuV9BRKF668alcjKQJN+9MmGan0UrHHvgr/j+CoRGRRDGdgUt+4By4I7bC7ECgYBBH9nFVHME1MoEGpechrrdkd2DJ/b49CAgsVcfwmpXOd/c7b6SIhSF9SGR3/cUC7rSsMRtFjynkPf2K9E/8NC+4tZStSSmONPNCpG5/aR/fbpzXkNJ3Eqs4Kybes1TSSJSVMj4JEVHTKGaiapT1mRXwce4NXhYjbOuPIxPpKTJiwKBgFJFEYV6lCnLvEvwMEBRaTwMqA+4Lmg3o19vTU/uuZQY1XH6mTxszw0yX+95fjN65wup9m6Tr2/2FjBWkVFq7qE6b4Isiszxhj+osQrWkuQSffo+9Ni8sJc7tdxFRQvCs80SPe3nMQWBCj3ZlsZ73rVSwIGAk4xaCGpAVvcIpjIZ";
    }
}