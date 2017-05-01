package bigdata.api.echart.datapush;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import framework.util.CollectionUtil;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.MagicType.Option;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Pie;

public class DataPushConvert {
	public static GsonOption getGsonPieOrFunnelOption(
			DataPushEntity<DataPushDistribution> entity) {

		GsonOption option = new GsonOption();
		if (entity == null)
			return option;
		List<DataPushDistribution> list = entity.getList();
		Tooltip tooltip = new Tooltip();

		tooltip.trigger(Trigger.item);
		tooltip.formatter("{a} <br/>{b} : {c} ({d}%)");
		option.tooltip(tooltip);

		Legend legend = new Legend();
		legend.orient(Orient.vertical);
		legend.x(X.left);

		List<String> legendData = getLegendData(list);
		legend.data(legendData);
		option.legend(legend);

		Toolbox toolbox = new Toolbox();
		toolbox.show(true);

		toolbox.feature(Feature.mark);
		toolbox.feature(Feature.dataView);
		MagicType magicType = new MagicType(Magic.pie, Magic.funnel);
		Option inOption = new Option();
		Funnel funnel = new Funnel();
		funnel.x("25%");
		funnel.width("0.01%");
		funnel.funnelAlign(X.left);
		funnel.max(1548);
		funnel.type();
		inOption.funnel(funnel);
		magicType.option(inOption);
		magicType.title();
		Restore restore = new Restore();
		restore.show(true);

		toolbox.feature(magicType);
		toolbox.feature(restore);
		toolbox.feature(Feature.saveAsImage.show(true));
		option.toolbox(toolbox);

		option.calculable(true);

		Pie pie = new Pie(entity.getDescription());
		pie.radius("55%");
		pie.center("50%", "60%");

		pie = pieSetData(pie, list);

		option.series(pie);
		return option;
	}

	private static List<String> getLegendData(List<DataPushDistribution> list) {
		List<String> legendData = new ArrayList<String>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (DataPushDistribution dis : list) {
				legendData.add(dis.getKey());
			}
		}
		return legendData;
	}

	private static Pie pieSetData(Pie pie, List<DataPushDistribution> list) {
		if (CollectionUtil.isNotEmpty(list)) {
			for (DataPushDistribution dis : list) {
				Data data = new Data();
				data.setName(dis.getKey());
				String value = dis.getValue();
				BigDecimal realValue = new BigDecimal(value);
				realValue = realValue.multiply(new BigDecimal(100));
				data.setValue(realValue.toString());
				pie.data(data);
			}
		}
		return pie;
	}

	public static GsonOption getGsonBarOrLineOption(
			DataPushEntity<DataPushDistribution> entity,
			DataPushExternalEnum dataPushEnum) {
		GsonOption option = new GsonOption();
		if (entity == null)
			return option;
		List<DataPushDistribution> list = entity.getList();
		option.title(dataPushEnum.getDescription());
		option.tooltip(Trigger.axis);
		Legend legend = new Legend();
		legend.data(dataPushEnum.getDescription());
		option.legend(legend);

		Toolbox toolbox = new Toolbox();
		Restore restore = new Restore();
		restore.show(true);
		MagicType magicType = new MagicType(Magic.line, Magic.bar);

		toolbox.setShow(true);
		toolbox.feature(Feature.mark);
		toolbox.feature(Feature.dataView);
		toolbox.feature(magicType);
		toolbox.feature(restore);
		toolbox.feature(Feature.saveAsImage.show(true));
		option.toolbox(toolbox);

		option.calculable(true);

		List<String> xAxisData = new ArrayList<String>();
		List<String> barData = new ArrayList<String>();
		for (DataPushDistribution element : list) {
			xAxisData.add(element.getKey());
			barData.add(element.getValue());
		}
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setData(xAxisData);
		ValueAxis yAxis = new ValueAxis();
		option.xAxis(xAxis);
		option.yAxis(yAxis);

		Bar bar = new Bar(dataPushEnum.getDescription());
		bar.data(barData.toArray());
		option.series(bar);
		return option;
	}
	
	
	public static GsonOption getGsonBarOrLineOption(
			List<DataPushEntity> list,
			DataPushExternalEnum dataPushEnum) {
		GsonOption option = new GsonOption();
		List<List<DataPushDistribution> > list2= new ArrayList<List<DataPushDistribution> >();
		List<String> list3=new ArrayList<String>();
		for(DataPushEntity entity:list){
			list3.add(entity.getTime());
			List<DataPushDistribution> list1= entity.getList();
			list2.add(list1);
			
			
		}
		option.title(dataPushEnum.getDescription());
		option.tooltip(Trigger.axis);
		Legend legend = new Legend();
		legend.data(dataPushEnum.getDescription());
		option.legend(legend);

		Toolbox toolbox = new Toolbox();
		Restore restore = new Restore();
		restore.show(true);
		MagicType magicType = new MagicType(Magic.line, Magic.bar);

		toolbox.setShow(true);
		toolbox.feature(Feature.mark);
		toolbox.feature(Feature.dataView);
		toolbox.feature(magicType);
		toolbox.feature(restore);
		toolbox.feature(Feature.saveAsImage.show(true));
		option.toolbox(toolbox);

		option.calculable(true);

		List<String> xAxisData = new ArrayList<String>();
		List<String> barData = new ArrayList<String>();
		for (List<DataPushDistribution> list1 : list2) {
			for (DataPushDistribution element  : list1) {
				//xAxisData.add(element.getKey());
				barData.add(element.getValue());
			}
		}
		
		for (String s: list3) {
			xAxisData.add(s);
		}
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setData(xAxisData);
		ValueAxis yAxis = new ValueAxis();
		option.xAxis(xAxis);
		option.yAxis(yAxis);

		Bar bar = new Bar(dataPushEnum.getDescription());
		bar.data(barData.toArray());
		option.series(bar);
		return option;
			
	}
}
