/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.auth;

/**
 *
 * @author crowl
 */
public class JwtConfig {
    
    public static final String LLAVE_SECRETA="alguna.clave.secreta.123456";
    
    public static final String RSA_PRIVATE="-----BEGIN RSA PRIVATE KEY-----\n" +
"MIIEpAIBAAKCAQEA2f82L0xTI+9mNLostXj8IpQVQklHXJneDjYC9L1YrkXcAkLw\n" +
"uyykz8impJOCqWJbAdFK+1Pe+seczCB7LOrl+75xlcxd5sqqhP2KBP62pFyUPVLn\n" +
"iFmtB2uQjNi7cE5H+DC3txe9KWDMfkNUAd4WZIyTlwYuUlrdycDgek3qvZ2LdTGN\n" +
"nqBdWNrU6EOLM+TfwDlm4mRo2MMQtfuNoXOWTTpx0YpQSrsM04JemfzRR53/zhgc\n" +
"gzL4QsYsW+aGHGtzh8VksUUswqO30NhhagbEGG9LYORnT+fyR6OYeDxUO7F3N3U5\n" +
"dWFzn0jBFN5Y7Dh3FBbORYRqsd9MzjzrtlFHjQIDAQABAoIBAQDRAKeBh2q6rD7Q\n" +
"VKKixkDuIg2TxM/fT0IfW9dPr+3DAAVkQautuKq28AgdB7QhE8RvJKr/9TR2OUTy\n" +
"TD3sHbWru7YIyaxJposAAFkAWvje8kfJ159WEawbzhwDfZOPo+3KXWL17pOUhVLp\n" +
"x0FWnQfZFRI3nFxy5t13Hi3Z9LQXX5wXANbw6OLC7rsLq9hOXnBix3jKTFNxvQiM\n" +
"6iRHMP7YiQGAV80yQ2NIqnSQRrsTtwa8/vcnFUQcZcBAuTBWCswfrUhGoBLZDUG8\n" +
"eEjpkl1l8zZnBhNcnwZzTWMZLAU0Y4lgiMCcSij3bNQmzy5YHK6CVsnA4zufUoxw\n" +
"RCCGG0ehAoGBAPhE3E0+93VR74hHMqeg/FnTaMX3kz4eLjlB3ZSqElZd4io5TFBI\n" +
"j99xMktSCu+YGK8HwKMOQqyh8llTI6iwiH+26wgq2TIF5uMGheuUi7pbB2+2mwA1\n" +
"FilOt3OwKunn1bTghMRLCDN6VDX7Cx89aqRcnpsWngysCcf6dYdvuWtpAoGBAODJ\n" +
"B5VdSxfRuBby38cMgrpbMj4Cu5DpbPk2oALL1jj3Oqz7ZqzyE6/PX9/eXt8c6Tgq\n" +
"fCR4jxo2Z0Y/y4CPKUUaq1o5Nmih8+qgJRuXzpLJGbpefaAeGQLzyAYAhVwp5fp5\n" +
"lBGFMa4UHwDXXBzeRPcRQvss29Q+h/14zTwvC2qFAoGBANDIyCPGEkD9eN04UucZ\n" +
"wV6hJXlmrNMOenz4YSmGpQkwxuQPb5gMjcGw5kTlaAD5/bgkYuI9OeEICFEKU/Cn\n" +
"FQOihqFFt4YT5ImDkNf/AkrkBqrx/AP+oPZ4FlsaCdzPdK86KS0SlIEGHQ+QYVgS\n" +
"RD6ZDWELcjrh6w/cFz6gvGQRAoGADwJdV0Pbd8OxHxcoignz0K22NR3JmGt9s0ZK\n" +
"eOVTcIpRiKtgt7uSRPL7297lRtCPYgi0MVs7cuyfCLnJxNPKVarqNiQL1gC6YLhA\n" +
"wg7o5DQ5W5BjowVfdisA+CWgwf6qNIdZs7HJF26Qg2Xd6K+Ou1CTwGHGa+2g9vPS\n" +
"AGrDwGECgYAo5FM3Symz3aysQo+ULu0of2swKL3rYCYPw+hDna2uuwBcFGIgOiaj\n" +
"6fMrf4/zl7SoHWi0tb+XKJcXkOKusATMg2rWLJVMEQ4vl4pPRHbbWRZ8TySjG6xS\n" +
"I/mzfDIGvz74Ep8Cr5H/CfHAto94XGWGovGVHYERpDLhe96Gcu16yg==\n" +
"-----END RSA PRIVATE KEY-----";
     public static final String RSA_PUBLIC="-----BEGIN PUBLIC KEY-----\n" +
"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2f82L0xTI+9mNLostXj8\n" +
"IpQVQklHXJneDjYC9L1YrkXcAkLwuyykz8impJOCqWJbAdFK+1Pe+seczCB7LOrl\n" +
"+75xlcxd5sqqhP2KBP62pFyUPVLniFmtB2uQjNi7cE5H+DC3txe9KWDMfkNUAd4W\n" +
"ZIyTlwYuUlrdycDgek3qvZ2LdTGNnqBdWNrU6EOLM+TfwDlm4mRo2MMQtfuNoXOW\n" +
"TTpx0YpQSrsM04JemfzRR53/zhgcgzL4QsYsW+aGHGtzh8VksUUswqO30NhhagbE\n" +
"GG9LYORnT+fyR6OYeDxUO7F3N3U5dWFzn0jBFN5Y7Dh3FBbORYRqsd9MzjzrtlFH\n" +
"jQIDAQAB\n" +
"-----END PUBLIC KEY-----";
}
