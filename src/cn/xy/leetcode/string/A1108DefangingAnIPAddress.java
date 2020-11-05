package cn.xy.leetcode.string;

/**
 * @author XiangYu
 * @create2020-11-04-13:32
 *
 *
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 *
 * A defanged IP address replaces every period "." with "[.]".
 *
 *
 */
public class A1108DefangingAnIPAddress {

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if(address.charAt(i) != '.'){
                sb.append(address.charAt(i));
                continue;
            }
            sb.append("[.]");
        }

        return  sb.toString();
    }

    public String defangIPaddr2(String address) {
        return address.replace(".","[.]");
    }

}
