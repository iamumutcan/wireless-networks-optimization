# CoordinateGrouping

This repository contains a Java program that groups devices based on their coordinates. The program takes two input files:

-   `DeviceList.txt`: This file contains a list of devices, each with its x-coordinate and y-coordinate.
-   `BaseStationList.txt`: This file contains a list of base stations, each with its x-coordinate, y-coordinate, and coverage radius.

The program outputs a HTML file that shows the devices grouped by base station.

## How to use

1.  Clone this repository to your computer.
2.  Open the  `src/CoordinateGrouping.java`  file in a Java IDE.
3.  Set the path to the  `DeviceList.txt`  and  `BaseStationList.txt`  files in the  `main()`  method.
4.  Run the program.
5.  The output HTML file will be created in the  `out`  directory.

## Example

The following is an example of the input files:
***DeviceList.txt:***
***X       -      Y***
-1.82 -4.40  
17.41 6.74  
17.47 -3.47  
-6.79 15.12
***BaseStationList.txt:***
***Max Device - Range***
600 100  
500 100  
500 90

## Output
``` 
Group with the most members: 4
Grup: [Device{id=0, x=-1.82, y=-4.4, isConnected=true}, Device{id=1, x=17.41, y=6.74, isConnected=true}, Device{id=2, x=17.47, y=-3.47, isConnected=true}, Device{id=3, x=-6.79, y=15.12, isConnected=true}]
X: 6.567500000000001 Y: 3.4974999999999996
Group with the most members: 0
Grup: null
X: NaN Y: NaN
Group with the most members: 0
Grup: null
X: NaN Y: NaN
Device{id=0, x=-1.82, y=-4.4, isConnected=true}
Device{id=1, x=17.41, y=6.74, isConnected=true}
Device{id=2, x=17.47, y=-3.47, isConnected=true}
Device{id=3, x=-6.79, y=15.12, isConnected=true}
true
false
false
```
**HTML OUTPUT:**
![enter image description here](https://lh3.googleusercontent.com/4gwGbDxbqGlzSfjAeyIHZG2sHBCacjzbsypE1A7gnMOXrpgpMCDtwr0dd1jimPo5OXwfMNr2oS1vRglHxfC89LLPz07jsGtmJ2bg66fIYD_3vWP_k_G18M-ZKmDzEmMTFJoJXr3UA4tdq65o8ZZkXE_iIp30kGfiPURd7kT7svAo_9F4pJ4kszcZz0eItOkrs4ZR7_Jz9Zw1mqNdmwb41Nq35iFVHcgWS7s52exqUT9SvFXPBD_xTW8-CKHgngrnHPdb7KNDiYWyFJY_Gt_q4I7kdbZWtM7HcRbU8CCrSq9_d11YXWKgfkOedvsahu-uPZLoL50DlbgYTpIbgxQvv-e7NjxJYJ-N58_BMQgg4vQFs_BOURLft277R1WJdubzvLXe9XjZwVz0hNds7aer2IF9QPVjPtBNc4Nlj-bj4kFRDoAWdFqxr7InEO1-zQEEpA1R0FGxkASIW3vAfkYCl_AVhrxfaYm_Wu97zQotF1QZAPv1K-W7Ap_6NXKpkFamAZh1uBaGSnp9rd1qLvdmimrTr_agfvKXibtTsEoGEikBZm5vVUCMo99mowZAUTnAlnJ60FSNIUrkFwevz7gcUctmgXVS4_TTceLQjUcs0lV_bSJRjl-YHdYdaE1K2sZkATyCJrz-_K3dqW79uvkQYEkvdjRauz4OFjiLJFvHm3yHlwN_pQoyeP8lQgDSv3FizglEioFO84fylghuW11vf8NChLFz9EjxJ_k4Rir9RE8rAg3rTIHmXbx80GCnOdxWFIw6B0mRiQVDfyiVxmoUSunNjOTeSzDd1H1BEIWQI0ExFlLgO0sAIt8TsqmFXB9ybQe0yMiz9MVnUuMKF3Lw0kAGFjk9JhAGm9BbX0Yc4G8lq-Fyjavsx4L0z5RgRXK_KY15VKMjfbc3Cff5ZgKKBHr5xjVjgSinEYfdXSRgSdix13sKCCxFAKxOHwk9W8f2S2bPrgxH3CSLEXm2vciZmbxPW1j3vp69fUXdvYNczUrgPwiBWrgDJRSw_rT4gBrobyMTd-xS0Z2T3PhwBcPI5mdE0Fuq69ZoWjMFEk28nawfsi9Z1gQtulMC8EFJJw=w1320-h729-s-no?authuser=4)
the result with different imputs:
![enter image description here](https://lh3.googleusercontent.com/Ky--_FY5bXL305KNbu3Jkd7-vf22xFj1yjpNcUuXbGQeX7j1mIheHVQErdYQlXSpWeJGEOCoRlKx-4xoT1zjGTy7E9Mzwwg8HxWZfoTsPkhqSrSUz9B5A1xT6jrQnL2d8blCogaCNAJCv3y1qve0NRs3-Z-6g37mIgtRRpO-uJugNou4e2Yj1PvP75A1UlRbs8LMKC8J2e0GC8_lNGKLZDg8ppX7MPkbVvjDU2Q0UtDq8BpSLfUyxpHcrPcFimWspLYIGPCeddGEd1e3AbQ-usJUpvgPINOJWoCAOz4UuyLJTZ5ctt0FVUvI3UogZJ0qpkSYbWu6CowPKJIQpQ0mPwRyvXr7Ff5KbY-MILMyDoSS8WCHBbLlDBa69H-zeQFGTamOcqkNkJ9HYC7D2BSqdqN8GWLiwUs-HOY9ldlQVQO-Eh0e4sMmugfWT7I_hf0Si04XR2k16bgEtP_aYJP4o24cznUTGT0o0K7c3iBXE5ZOc5JwsU_s7ArB8gSvtPuKMmSr64OwqvRa2oryv8HHb1LZyixDsH1oAIIMIf4R3xIoHiE_2jDCJpfaFhHB8K0z2-R1yYw308IHfakC_br1Xp95ew9MXMeCqmc0pB0yRqLrrUZKP8nDqCxOdFHpv35ZPE8fb44X38WsfC4gMhjWEFMXW-oTr4zN3eaopiTqP8ar6Rtq1d7oDW5JTiON39BvTjwY86FkE8QKeIVDpasykg3qg9EqnpLalowM8u2Extt6frO1X3LlWlQxs52hzrTO2XumdY7hshwX0VJ4yt8aJWW-7YzkcgO657jmmJlVpsLpGMVLVzyTiIQjOJk9xZRcX3_aZTDgtc8iIjv0TOOGt3Ou0N0oPumiIYPuyP1QMG4Rh_rAlhTbw3aXdP0C84Wn_ryeZ7NfeDYx_dLXP7lLGaJVw5Qp9-m1GkovWZrZ8RL3tTmSrWpXgDN1BEKm5l3AsyTHrXQn8NsYZvmJD9ZFF3PGuaK69YF-vfeDh3uwRL5EZfIHFQdgMGgB3VqTVZ-7ryS7oAMlOVgBhePEJTAfW1Xl-N4mflrifr7WfY8GRWHvCPuyI4F-QbVUWJFq0g=w1620-h885-s-no?authuser=4)
