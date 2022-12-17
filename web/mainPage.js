// 类型对应的字符串
const typeStrObj = {
  income: "收入",
  payment: "支出",
};

// 分类对应的字符串
const purposeStrObj = {
  medical: "医疗",
  education: "教育",
  dining: "餐饮",
  daily: "娱乐",
  entertainment: "日常",
  other: "其他",
};

// 表格数据
var tableData = [
  {
    time: "2022-06-12",
    amount: "3",
    type: "payment",
    typeStr: "支出",
    purpose: "medical",
    purposeStr: "医疗",
  },
  {
    time: "2022-06-15",
    amount: "1",
    type: "payment",
    typeStr: "支出",
    purpose: "dining",
    purposeStr: "餐饮",
  },
];

// 获取元素(元素，是否获取全部符合的元素列表)
const getDom = function (dom, isAll) {
  if (isAll) {
    return document.querySelectorAll(dom);
  }
  return document.querySelector(dom);
};

// 初始化echarts实例
var myChart = echarts.init(document.getElementById("main"));
var myChartLine = echarts.init(document.getElementById("mainLine"));
var myChartPie = echarts.init(document.getElementById("mainPie"));

// 渲染图表
const onRenderChart = (tableData) => {
  let medicalSum = 0;
  let educationSum = 0;
  let diningSum = 0;
  let dailySum = 0;
  let entertainmentSum = 0;
  let otherSum = 0;
  tableData.forEach((item) => {
    if (item.type === "payment") {
      switch (item.purpose) {
        case "medical":
          medicalSum += Number(item.amount);
          break;
        case "education":
          educationSum += Number(item.amount);
          break;
        case "dining":
          diningSum += Number(item.amount);
          break;
        case "daily":
          dailySum += Number(item.amount);
          break;
        case "entertainment":
          entertainmentSum += Number(item.amount);
          break;
        default:
          otherSum += Number(item.amount);
      }
    }
  });

  // 柱状图
  var option = {
    title: {
      text: "类型柱状图",
    },
    tooltip: {},
    legend: {
      data: ["支出"],
    },
    xAxis: {
      data: ["医疗", "教育", "餐饮", "娱乐", "日常", "其他"],
    },
    yAxis: {},
    series: [
      {
        name: "支出",
        type: "bar", //柱状图
        data: [
          medicalSum,
          educationSum,
          diningSum,
          dailySum,
          entertainmentSum,
          otherSum,
        ],
      },
    ],
  };

  // 折线图
  var optionLine = {
    title: {
      text: "日期折线图",
    },
    tooltip: {},
    legend: {
      data: ["开销"],
    },
    xAxis: {
      data: ["医疗", "教育", "餐饮", "娱乐", "日常", "其他"],
    },
    yAxis: {},
    series: [
      {
        name: "开销",
        type: "line", //折线图
        data: [
          medicalSum,
          educationSum,
          diningSum,
          dailySum,
          entertainmentSum,
          otherSum,
        ],
      },
    ],
  };

  // 饼图数据
  var optionPie = {
    title: {
      text: "分类饼状图",
    },
    series: [
      {
        name: "类型占比",
        type: "pie",
        radius: "55%",
        roseType: "angle",
        data: [
          { value: medicalSum, name: "医疗" },
          { value: educationSum, name: "教育" },
          { value: diningSum, name: "餐饮" },
          { value: dailySum, name: "娱乐" },
          { value: entertainmentSum, name: "日常" },
          { value: otherSum, name: "其他" },
        ],
      },
    ],
  };

  // 显示图表。
  myChart.setOption(option);
  myChartLine.setOption(optionLine);
  myChartPie.setOption(optionPie);
};

// 绑定表格操作事件
const onBindTableEvent = (tableData) => {
  // 编辑数据,查找数据后,返显
  getDom(".editBtn", true).forEach((item) => {
    item.addEventListener("click", function () {
      const index = Number(this.getAttribute("data-index"));
      const tableInfo = tableData.find((item, idx) => index === idx);
      getDom(".modal").setAttribute("data-eventType", "edit");
      getDom(".modal").setAttribute("data-index", index);
      getDom("#time").value = tableInfo.time;
      getDom("#amount").value = tableInfo.amount;
      getDom("#type").value = tableInfo.type;
      getDom("#purpose").value = tableInfo.purpose;
      getDom(".modal").style.display = "block";
    });
  });
  // 删除数据,重新渲染页面
  getDom(".deleteBtn", true).forEach((item) => {
    item.addEventListener("click", function () {
      const index = this.getAttribute("data-index");
      tableData.splice(index, 1);
      onRenderTable(tableData);
    });
  });
};

// 渲染表格
const onRenderTable = (tableData) => {
  var tableStr = `<tr>
    <th>日期</th>
    <th>金额</th>
    <th>分类</th>
    <th>备注</th>
    <th>操作</th>
  </tr>`;
  tableData.forEach(function (item, index) {
    tableStr += `<tr>
        <td>${item.time}</td>
        <td>${item.amount}</td>
        <td>${item.typeStr}</td>
        <td>${item.purposeStr}</td>
        <td>
          <div class="option">
            <div class="btn editBtn" data-index="${index}">编辑</div>
            <div class="btn deleteBtn" data-index="${index}">删除</div>
          </div>
        </td>
      </tr>`;
  });
  getDom("table").innerHTML = tableStr;
  onBindTableEvent(tableData);
  onRenderChart(tableData);
};
onRenderTable(tableData);

// 绑定新增事件,点击弹出弹窗
getDom(".addBtn").addEventListener("click", function () {
  getDom(".modal").setAttribute("data-eventType", "add");
  getDom(".modal").style.display = "block";
});

// 绑定弹窗关闭事件,清空表单
getDom(".closeBtn").addEventListener("click", function () {
  getDom("#addForm").reset();
  getDom(".modal").style.display = "none";
});

// 绑定弹窗确定事件,添加/编辑数据,清空表单,关闭弹窗,重新渲染页面
getDom(".confirmBtn").addEventListener("click", function () {
  // 获取表单数据
  const time = getDom("#time").value;
  const amount = getDom("#amount").value;
  const type = getDom("#type").value;
  const purpose = getDom("#purpose").value;
  // 都存在值才进行操作
  if (time && amount && type && purpose) {
    const eventType = getDom(".modal").getAttribute("data-eventType"); // 操作类型
    const index = getDom(".modal").getAttribute("data-index"); // 操作的index
    // 要保存的数据
    const data = {
      time,
      amount,
      type,
      typeStr: typeStrObj[type],
      purpose,
      purposeStr: purposeStrObj[purpose],
    };
    // 新增的，push表格数据
    if (eventType === "add") {
      tableData.push(data);
    }
    // 编辑的，修改对应index的表格
    if (eventType === "edit") {
      tableData.splice(index, 1, data);
    }
    getDom("#addForm").reset();
    getDom(".modal").style.display = "none";
    onRenderTable(tableData);
  } else {
    alert("请输入完整的信息");
  }
});

// 绑定筛选事件
getDom(".filterBtn").addEventListener("click", function () {
  var filterTableData = JSON.parse(JSON.stringify(tableData));
  // 获取筛选表单数据
  const startTime = getDom("#starttime").value;
  const endTime = getDom("#endtime").value;
  const filterType = getDom(".filterType").value;
  const filterpurpose = getDom(".filterpurpose").value;
  //   不筛选
  if (!startTime && !endTime && !filterType && !filterpurpose) {
    return;
  }
  //   筛选时间
  if (startTime && endTime) {
    filterTableData = filterTableData.filter(
      (item) => item.title > startTime && item.title < endTime
    );
  }
  //   筛选类型
  if (filterType) {
    filterTableData = filterTableData.filter(
      (item) => item.type === filterType
    );
  }
  //   筛选分类
  if (filterpurpose) {
    filterTableData = filterTableData.filter(
      (item) => item.type === filterType
    );
  }
  onRenderTable(filterTableData);
});
