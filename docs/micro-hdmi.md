# Micro HDMI Port

As shown on the [Hardware](./hardware.md#main-logic-board-mlb) page, a
Micro HDMI solder pad is present on the Main Logic Board.

During an attempt to solder a Micro HDMI port to the exposed contacts we
damaged one of the solder pads, namely the CEC pad.
Luckily, this channel is not required according to the HDMI specification.

As we did not succeed in soldering an entire Micro HDMI port to the pads,
we soldered enamelled copper wire to each pad and connected them to an
HDMI breakout board.

<a href="./assets/overview_hdmi_setup_20230111_2101.jpg">
<img src="./assets/overview_hdmi_setup_20230111_2101.jpg" width="700"
    alt="Test setup with HDMI breakout board">
</a>

<table>
<tr>
<td>
<a href="./assets/PXL_20230110_130956832.jpg">
<img src="./assets/PXL_20230110_130956832.jpg" width="300"
    alt="Enameled copper wire soldered to HDMI contacts on MLB.">
</a>
</td><td>
<a href="./assets/PXL_20230111_142248076.jpg">
<img src="./assets/PXL_20230111_142248076.jpg" width="300"
    alt="Jump wires connected to HDMI breakout board.">
</a>
</td>
</tr>
</table>

We connected a display to the HDMI breakout board to see if the Echo Show 15
would show any output, but it remained dark.
Also, after connecting a video source there was no reaction.