# SignatureHandler
Program to sign and verify signatures using BouncyCastle as a provider.

## Requirements
* You have to set up a Java Keystore (JKS) in the appropriate format. In my case BouncyCastleKeyStore (BSK).  

Within the store you need to generate 
1. Keypair
2. Certificate

* Make Provider available. Either statically within the code (as I did) or dynamically within you JRE.

## Adjustments 
* Don't forget to change the `keyStorePath` and `keyAlias`
* Other Brainpool algorithms can be found here: https://bouncycastle.org/specifications.html
