package org.spring.boot.test.base;

import com.google.common.collect.Lists;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.spring.syncdbtoredis.ConnectUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-30
 */
public class TestDbUtils {
    private List<String> names = Lists.newArrayList("松漪艺", "伏惠芳", "葛克韧", "姚楚霁", "陆崇勉", "宫晟旺", "范翠歌", "卓俏娣", "莫婉情", "万彦攀", "樊咪朦", "云素芯", "祖润严", "时莺菡", "舒轶子", "武千铭", "滑岱歌", "时裙谦", "华微红", "苗萱婧", "嵇钧娥", "虞宸冶", "窦菁凤", "吴秦权", "韦柳傲", "白典恒", "韩萌连", "龙达昀", "毕琚婷", "幸隽婧", "任嵩艺", "常秀姿", "杨景克", "曹赞霖", "潘菲冰", "庞岳韧", "舒彬翰", "陆运琦", "成强天", "严诗泽", "荣苹晓", "成蒙钧", "伏武波", "廉响牧", "韦微竹", "丁丹珍", "邵冶羚", "农娥杏", "萧珏元", "杭汝格", "汪杉钟", "郁玺飞", "仰韶冉", "韶婕瑗", "宁友鲲", "缪睿艾", "屈钥钰", "宫然姝", "周一嫒", "柯帅启", "符焕崴", "褚卿馨", "邴海舒", "赵英伟", "苏发澜", "贾宾展", "萧直将", "莫忠渝", "金革重", "经珠暖", "屈焕焰", "严嘉珑", "荣锦硕", "贾颉青", "秦格玲", "江俐鸽", "孔雄韦", "董沛怡", "宁涌桥", "魏珏碧", "潘毓汝", "毕星桢", "明震聪", "温蓉瑾", "金笑菁", "汪意全", "松凌贤", "岑娅芯", "刁婉琼", "常颜振", "花胡勉", "龙芹晗", "钟愉霁", "瞿钥甜", "明宾宸", "殷昆莹", "邓俊彭", "舒惠娟", "裴灿耀", "姚里洋", "彭昭妮", "王果畅", "乔鸿栋", "嵇润晨", "苗漪苏", "怀钥柳", "黎冰鹭", "杭岗柯", "虞皎盼", "卢凌艺", "江怡轶", "侯庭迁", "幸虹萍", "缪澄嵘", "宫冶立", "平里锴", "俞妮雪", "管栩闻", "盛斐沁", "宫辉臻", "苏戈蒙", "韶果绚", "唐清庭", "翁飞蓬", "丁城煦", "姬海琳", "计力跃", "蒋疆新", "任爽暖", "凤露彤", "华漫晔", "邴希谦", "江令楚", "赖敏悦", "时竹心", "谭怡旎", "尚垒益", "汪茜瑜", "屈良言", "甄依殉", "魏好素", "束仁青", "黄晴娴", "杭鹰煜", "郑洋美", "王蔷诚", "奚怀迁", "羿诺举", "曹庄贵", "班露敏", "温彦河", "高飙发", "束昊垚", "焦彩娟", "单娆逸", "强美好", "汤品研", "贡成秦", "段鲁奎", "侯蕊好", "龚兵驰", "屈齐然", "奚凡菊", "阮柯羽", "农灿煦", "叶萌韵", "金璟栋", "方闽创", "吴艾纯", "束洲溢", "计喜进", "贾其锟", "周珍希", "严妃嫒", "武花婕", "顾盛煊", "唐翰湛", "何殉侃", "叶娉彩", "齐雄琥", "金震桢", "陶贝骅", "伍瑗品", "支凌竹", "缪可舟", "朱耘旭", "毛多雯", "方达杨", "岑妲蔓", "邱添起", "施筝轶", "傅祯战", "孟筱思", "甄妲凤", "潘洋雯", "房沙畅", "崔鸽婉", "顾岭岭", "李歆钧", "温桐知", "毛雅洁", "宁焘嵩", "武戈纳", "蓬沫玥", "蔡煦婧", "杜嘉歆", "梅渝毓", "冯果翠", "宣铃贞", "滕强浪", "孙卿霓", "戴柯航", "石丽琴", "傅晔艾", "龚春韶", "伊幸多", "怀冲泓", "龚凝彬", "傅松钧", "李逊震", "娄逊意", "纪思亚", "郝晴颜", "孙霏晟", "姚卓联", "胡普顺", "马茵焕", "单萱诗", "苗凝秋", "奚音晗", "骆柳垚", "焦芳励", "房琰拓", "吉骥柯", "施学广", "班妹榕", "章举想", "沈瑛骊", "宣允泽", "蒙卉瑜", "谢韬肖", "沈珍侃", "宁菡朵", "李姿影", "鲍耘婕", "宋尉锬", "水定魁", "陈瑜沛", "詹钦臣", "何旎姬", "骆仁翰", "苏旗博", "水沫祺", "霍轶苏", "孙燕秀", "钱习荔", "张鉴韬", "姜铃竹", "杜泓宁", "韩妃贞", "孔李卉", "娄昕千", "管耘丽", "解树旺", "洪菁婧", "岑灵军", "章冠廷", "房寒普", "云皓雁", "范季千", "葛添廷", "吕俐千", "武吟蕾", "骆直德", "裘晟珍", "诸克川", "乔泓姣", "庞姝鸽", "童戈洋", "殷姝朵", "符泉默", "晏均天", "卓格涛", "蔡鹃竹", "张赞争", "孔硕庚", "尤明青", "丁唯腾", "宣咪笛", "奚江连", "穆融嵘", "韶育凯", "俞展辛", "宗澄力", "方歌月", "段鉴锋", "钱泽幸", "郭烽兵", "萧杨娇", "娄鸣文", "虞莉格", "乌发辛", "王甜俪", "周国琼", "卓军律", "嵇岳向", "戴来育", "金毓任", "房珑歆", "汤娓振", "戚庆千", "左赞强", "彭蕙晓", "成焕展", "乌晟仲", "左奇骊", "阮恩通", "管墨盼", "章河琛", "霍李慈", "邬元锁", "经杉熠", "时楚想", "尚同柱", "程微栋", "时沛朵", "平楠建", "解姿伶", "陆雍禹", "段韶香", "柏达雄", "戴胡万", "庞非铎", "何铖英", "明严竹", "卓亚翊", "仲千钰", "水娅蒙", "黎雪恋", "严俏雅", "费深桢", "倪颂嵘", "赵书静", "农战众", "翁直宇", "温焰艾", "宫澄姝", "祝煦喻", "奚昕云", "邴会骊", "倪尉方", "祁欢情", "祝菡一", "水灵洋", "司芹李", "魏红飙", "卢河锁", "颜品子", "盛岱胡", "戴丞泓", "奚璇展", "叶盼磊", "叶妹忱", "沈瑾冉", "吉望碧", "冯芊盈", "奚洋或", "凌兰佳", "邴美碧", "石璇格", "邱子秀", "郑花惟", "尹露纳", "支庄嵩", "殷杉琪", "冯嫚菊", "白竹慈", "富友鉴", "缪玫倩", "水励焰", "贾唯铭", "尤令臻", "伍丁英", "郑微雅", "凤耘俪", "陶湘妹", "左朦芬", "裴妙妍", "高想旎", "殷桑富", "明稳申", "束茉姝", "任傲迅", "董山帆", "樊铖冶", "雷宾昆", "葛畅同", "卢碧吟", "羿情凤", "仰开柏", "祝璐纳", "仲崴平", "伏珣舟", "吴奔融", "时裙震", "怀双桢", "邵晨想", "虞研薇", "农琚蒙", "顾甜日", "范达跃", "翁奕子", "曹磊年", "殷裕凡", "沈畏谊", "乌婉虹", "俞剑焰", "劳宁斯", "水歌达", "朱跃沙", "黄苹娓", "翟炯萌", "蓬逸斯", "徐庆莹", "郦旖骊", "刁革其", "谢容晨", "何雪凡", "焦绮令", "昌喻唯", "倪霏露", "姜殉洵", "祁喆梁", "司曦亚", "钟灵安", "贡研璋", "凌或琚", "房璇垚", "冯朴胡", "崔文铃", "喻泽歌", "经宙羿", "昌翰薇", "雷慧卿", "刘妮希", "邢爽玲", "周暖京", "崔盈汝", "王艾静", "鲍一俐", "戚椒泽", "宣泳柳", "惠铭骞", "束依娆", "温雄雷", "戚意忻", "卓普融", "时娆伶", "喻蔚蕙", "刁钰起", "时宜清", "时笑燕", "怀奎翼", "祁格蔚", "邵佳娴", "瞿骥勉", "乔瑛冶", "郦蓓宜", "曹莎眉", "孟卿湘", "朱李瑶", "柏沁心", "伏林萱", "娄宇颂", "房岳贝", "陶泓灵", "白榕珊", "邱鲁桥", "龙功其", "贡劫延", "花季明", "章疆成", "倪研庆", "童凝童", "凤昌莹", "马虹津", "丁霏瑶", "贺晏凡", "侯霞樱", "伊茵珣", "施超靖", "鲁清朴", "钟骥崇", "毛千晓", "沈明珍", "惠如廷", "李为鸽", "蓬珩锬", "许影茹", "刘姬旎", "孟新伟", "宁雪情", "巫岭娓", "郎崇华", "盛喆海", "周化礼", "戴镇燃", "皮妹嵘", "钟令冰", "余鹭樱", "余漪亚", "穆雯爽", "董声辛", "平武朴", "卓李悦", "赖义实", "牧樱灵", "戚好蓓", "秦蕾琪", "蒋珍可", "贺松琥", "舒汉闯", "伏瑗蕙", "韶苏睿", "廉策禹", "段沫骊", "温娉竹", "卫霖轲", "廉婧茜", "俞妮婧", "韶彬申", "吉华泓", "施倩荔", "郭安恒", "宫律岩", "经雍宾", "谢啸琨", "宗波仁", "解樱鹃", "明芝婵", "汪旺来", "李潮日", "施意霞", "幸延宸", "牧熠奕", "戴果璇", "龙旖冶", "姚庚恒", "霍书昕", "宣桥畏", "侯琥垚", "万丰安", "郭婕珏", "谢乐莲", "俞靖有", "伍懿习", "刘轶蕊", "李颜立", "毕桢巧", "黄昭妙", "诸奔艺", "羿格宝", "刘玉艾", "高瑶兰", "陈凝汝", "翁秋芹", "翟谊章", "谢纲洵", "廉笛冰", "伍韶俭", "张习萌", "谭联全", "廉樱书", "裴良俭", "成蒙慧", "祝轩俊", "羿舒连", "沈眉馨", "纪良侃", "伍盟舒", "韩媛素", "方琳雅", "岑娴桔", "瞿舒舜", "江灵绚", "吴妙鹭", "方容书", "吴典榕", "舒忠艺", "戚娣迅", "侯艺盼", "鲍伦西", "虞朔希", "束钥桢", "韦明澄", "卢迅珑", "霍沫俏", "万雁奔", "高溪伦", "白芹碧", "计芹铃", "冯宸锬", "侯莺冉", "罗宁齐", "潘碧椒", "窦民聪", "司意军", "滑杨戈", "强蓓瑾", "何苏愉", "时萌朦", "嵇焕玮", "刘思洵", "屈英曼", "裴玉佩", "夏珍诗", "霍依莲", "富沙钦", "卢冰韶", "何讳奔", "尹博晔", "祝鸿赞", "蒋定承", "牧伶如", "滑武朴", "明连福", "范拓颢", "潘连波", "柳芮辰", "夏妙敏", "徐京轲", "殷立菱", "滕义卉", "郭绚迅", "穆凯易", "缪庭稳", "沈晶茉", "皮桢薇", "骆顺轶", "胡闽争", "柏吉铭", "祁林绮", "郁典羽", "傅亚路", "申红恬", "裴锟朝", "白连晨", "叶忆菁", "怀妃璐", "黎研童", "庞葵皓", "申皎纳", "贾达栋", "叶姝颜", "纪沙令", "鲍蒙贤", "缪靖耿", "伍洵露", "房灿秋", "翁亚晗", "马勤明", "郭鼎城", "伍卓辰", "花城雷", "叶华亚", "范宝甜", "滑博旗", "邢桢黎", "郎苏梦", "蒙心梅", "皮爽崇", "彭昊旻", "史尚力", "皮蔚臣", "宋桑帅", "强雪筝", "汤正怀", "甄驰东", "廉显霄", "瞿陵纲", "邬骥谊", "申泽行", "牧苏宁", "宣澄泓", "张妍绚", "姚维芸", "穆畅沙", "单刚颢", "时会绚", "温爱菡", "水月言", "戚芹茜", "冯旖元", "解嵘鲁", "温程椒", "屈瑛愉", "尹焱津", "松朦琼", "谭奇舟", "宁孟韦", "瞿钰炳", "贺锬珑", "仰巧凌", "晏潮龙", "谢莹盼", "邹丹霁", "解真芝", "殷鹤宾", "曹念敏", "钱彬昀", "侯歆彤", "焦望俊", "龚兵安", "卢操礼", "幸晟煦", "胡莲绮", "廉丽华", "李璇恋", "吕一婷", "姚格沙", "程鉴兵", "井珍冶", "赵泉静", "阮淳花", "屈梅瑾", "毛坦潇", "褚旭朵", "余达路", "宋昌鼎", "富桐萌", "蒙昌锦", "谭炯琦", "时月操", "苗敏霏", "毛艳骊", "华嫚跃", "沈妤灵", "余心蕾", "褚典营", "华佳柏", "苏杉茹", "宗肠肖", "符恺波", "荣莹翼", "柳良瑛", "郭女暖", "嵇延炯", "孔玫琳", "施化崴", "松瑜隽", "邱建朴", "杭女玉", "谢野菊", "李卉蒙", "梅宾剑", "宁佩暖", "段蔓洁", "何意敏", "蒙想跃", "夏予霁", "云玥旎", "杨品俭", "董声隆", "段珍向", "钱楷晔", "窦嵘平", "郑心奕", "孔斐岭", "纪润筝", "祖景沛", "殷全忱", "晏成添", "芮琦城", "韦廷孝", "舒海力", "卢韧想", "翟旦源", "姜哲澜", "昌赢麟", "何可惠", "叶登怀", "韦淑杨", "秦蔚珊", "盛曦敏", "黎熙楚", "卓轶露", "阮宝裙", "翁连嵘", "经珏霞", "宫锟健", "乌妲义", "郝银双", "苏全纪", "班新珉", "常毓浩", "郜策涛", "姜朴虎", "童坤纪", "宣创民", "段垚尚", "盛雨昆", "盛杉千", "李妍卉", "凤或霓", "童茜蔚", "阮赢习", "毛罡盼", "成荷姝", "贺宜苓", "郝野宜", "贾洁湘", "贾远一", "田歆煦", "姚超重", "昌熙意", "鲁江鲁", "单岗枫", "左璞皓", "乔娣希", "马格翼", "邹日树", "窦格轶", "武列琨", "宣知纯", "农宽臻", "龙硕锁", "柳重律", "严姝连", "时纲榕", "盛冉悦", "庞谨惟", "童迎操", "邓咪雪", "幸朝治", "芮禹亚", "夏宜澄", "谭爱惠", "郑庆西", "蒙群振", "孟梦婵", "宁暖娥", "嵇煦栩", "焦昭多", "祝才易", "贡笛研", "戴烈剑", "宁纯泳", "虞昶如", "曹格芊", "邓钦胤", "鲁俭可", "华想锋", "王焕南", "许品茜", "汤姬美", "井毅川", "钟臻崴", "董辛晔", "怀虎丛", "束战富", "郦璇沁", "奚知娓", "苗清律", "嵇唯罡", "梅骞舟", "鲍裙弋", "卢卿骁", "仲伊莺", "毕鲁丁", "余会兰", "伍震恒", "曹州创", "骆晏柳", "秦昶帆", "成赫毓", "左忠璟", "夏涛灿", "杨依隽", "屈斐剑", "唐驰能", "苏振含", "花敏雪", "巫蕾梓", "袁云灵", "田迅芯", "瞿闻旭", "奚劲典", "尤汉唯", "顾剑妍", "左柏财", "岑嵘彤", "滑思洵", "劳茂宣", "吉娓玉", "何君江", "邹隽娟", "宗盼讳", "卓韵存", "水垒声", "羿晔霁", "屈娟滢", "叶弛韵", "宋嘉朦", "荣齐里", "晏川继", "庞言舒", "黎靓琪", "费学添", "许好蜜", "凤澄惟", "童子红", "雷蒙令", "奚万啸", "萧静情", "岑淮运", "章姣筝", "陈发骥", "单将研", "牧珣菡", "彭蓉鹭", "巫义咪", "娄博懿", "柳诺宾", "虞洋贞", "董婵彤", "白李莉", "郁丽想", "松念冰", "龚乾福", "伏冕飞", "屈嫱韵", "梅庭虎", "汤煜煜", "计崧枫", "乔琼嵘", "姜杏唯", "经慈菡", "解瀚运", "成椒连", "汪翼登", "祖笑晔", "方伟逊", "贡灏北", "余璇伦", "刘如飚", "毛如鹃", "戚行普", "殷毓枫", "邴冉霞", "蒙玺令", "荣万鸿", "段冉昭", "严俐俪", "裘卉宁", "顾林默", "余开锋", "滑辉礼", "葛涓虹", "郜桑金", "尤渊骏", "殷为潇", "陆馨湘", "邢礼斌", "束星淑", "荣珣心", "盛蒙怡", "纪萌莺", "吕甜伶", "庞芯凌", "阮超均", "赖殉妲", "邱铎纲", "余章珍", "韶萌钥", "柳耘亭", "施崇亚", "夏为理", "班保其", "褚娟晗", "尚璐冉", "任操霞", "惠韶一", "倪璇童", "伍咪凝", "葛发迁", "张韶有", "明丛利", "严东耀", "井琪洋", "邢湛满", "明勃会", "朱汝皎", "成煊业", "松才昌", "吴伦羿", "张澄梦", "华瑶晟", "杨鹏肖", "穆元冶", "殷迁肠");

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");

        System.out.println("===== test1 =====");
        Connection conn = ConnectUtils.connect();
        long start = System.currentTimeMillis();
        randomInsert(conn, 100000);

        // 大概1s可以加载全部数据
        System.out.println("从mysql中加载数据耗时：" + (System.currentTimeMillis() - start));
    }

    private void randomInsert(Connection conn, int total) throws SQLException {
        QueryRunner totalRunner = new QueryRunner();
        // totalRunner.insertBatch()

        int count = 0;

        do {
            count++;
            int a = RandomUtils.nextInt(1, 1000000);
            int b = RandomUtils.nextInt(1, names.size());
            String name = names.get(b);
            int c = RandomUtils.nextInt(1, 1000000);
            int d = RandomUtils.nextInt(1, 1000000);
            totalRunner.insert(conn, "insert into test_20230830(a, b, c, d) values(?,?,?,?)", new ScalarHandler<>(), a, name, c, d);
        } while (count < total);
    }

}

