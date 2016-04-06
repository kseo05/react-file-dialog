require("../css/base.css");
require("../css/react-bootstrap-table.css");
require("../css/toastr.css");

// TODO CommonJS 으로 모듈 정의.
(function(global, $, React, BootstrapTable, TableHeaderColumn) {
  "use strict";

  class FileDialog extends React.Component {
    //######################### Accessors Begin ###########################
    //######################### Accessors End #############################

    //######################### Main Functions Begin ######################
    constructor (props) {
      var cloneProps = $.extend({}, props, true);
      cloneProps.rootPath = FileDialog.refinePath(cloneProps.rootPath);
      super(cloneProps);

      try {
        var tableData = this.getDirInfo(this.props.rootPath).list;
        this.state = {
          "path" : this.props.rootPath,
          "tableData" : tableData
        };
      } catch (e) {
        throw e;
      }
    }

    // TODO Table 주위에 테두리나 배경색 넣기.
    render () {
      return <div data-signature="Yeop's React File Dialog Tracer Bullets">
        <div className="row clearfix">
          <div className="column col-md-1">
            <button type="button" className="btn btn-default"
              style={{"width" : "100%", "float" :"left"}}
              onClick={() => this.onBtnUpperDirClick()}>
              ◀
            </button>
          </div>
          <div className="column col-md-1">
            <button type="button" className="btn btn-default" style={{"width" : "100%"}}
              onClick={() => this.onBtnRefreshClick()}>
              *
            </button>
          </div>
          <div className="column col-md-10">
            <input type="text" className="form-control" style={{"width" : "100%"}}
              placeholder=".col-md-11" readOnly={true} value={this.state.path}>
            </input>
          </div>
        </div>
        <div className="row clearfix">
          <div className="column col-md-12">
            <BootstrapTable data={this.state.tableData} striped={true} hover={true}
              pagination={true} columnFilter={true} search={true}
              options={{ "onRowClick" : (row) => this.onRowClick(row) }}>
              <TableHeaderColumn dataField="type" dataSort={true} dataAlign="center"
                width="40px" >
              </TableHeaderColumn>
              <TableHeaderColumn isKey={true} dataField="name" dataSort={true}
                dataFormat={ (cell, row) => this.formatFilename(cell, row) }>
                Name
              </TableHeaderColumn>
              <TableHeaderColumn dataField="createdTime" dataSort={true} dataAlign="center"
                width="150px" dataFormat={ (cell, row) => this.formatDatetime(cell, row) }>
                Created
              </TableHeaderColumn>
              <TableHeaderColumn dataField="modifiedTime" dataSort={true} dataAlign="center"
                width="150px" dataFormat={ (cell, row) => this.formatDatetime(cell, row) }>
                Modified
              </TableHeaderColumn>
              <TableHeaderColumn dataField="fileSize" dataSort={true} dataAlign="center"
                width="100px" dataFormat={ (cell, row) => this.formatFilesize(cell, row) }>
                Size
              </TableHeaderColumn>
            </BootstrapTable>
          </div>
        </div>
      </div>;
    }

    setStateByPath (path) {
      try {
        this.setState({
          "path" : path,
          "tableData" : this.getDirInfo(path).list
        });
      } catch (e) {
        throw e;
      }
    }
    //######################### Main Functions End ########################

    //######################### Formatters Begin ##########################
    formatFilename (cell) {
      return "<b>" + cell + "</b>";
    }

    formatDatetime (cell) {
      var result = cell;
      var date = new Date(cell);

      if (date) {
        result = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()} `
        + `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      }

      return result;
    }

    formatFilesize (cell,row) {
      if (this.isFile(row.type)) {
        return this.humanFileSize(cell, true);
      }
      return "";
    }

    humanFileSize (bytes, si) {
      // http://stackoverflow.com/questions/10420352/converting-file-size-in-bytes-to-human-readable
      var thresh = si ? 1000 : 1024;
      if(Math.abs(bytes) < thresh) {
          return bytes + " B";
      }
      var units = si
          ? ["kB","MB","GB","TB","PB","EB","ZB","YB"]
          : ["KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"];
      var u = -1;
      do {
          bytes /= thresh;
          ++u;
      } while(Math.abs(bytes) >= thresh && u < units.length - 1);
      return bytes.toFixed(1)+" "+units[u];
  }

    //######################### Formatters End ############################

    //######################### Event Handlers Begin ######################
    onRowClick (row) {
      if (this.isFile(row.type)) {
        this.props.executeFile(this.state.path + row.name, row.name);

      } else if (this.isDirectory(row.type)) {
        this.setStateByPath(this.state.path + row.name + "/");
      }
    }

    onBtnUpperDirClick () {
      var dirFields;
      if (! this.isRootPath()) {
        dirFields = this.state.path.split("/");
        dirFields.pop();
        dirFields.pop();
        this.setStateByPath(dirFields.join("/") + "/");
      } else {
        this.setStateByPath(this.state.path);
      }
    }

    onBtnRefreshClick () {
      this.setStateByPath(this.state.path);
    }
    //######################### Event Handlers End ########################

    //######################### Data Utils Begin ##########################
    isRootPath () {
      return (this.state.path.length === this.props.rootPath.length);
    }

    static refinePath (path) {
      var result = path.replace(/\\\\/g, "/");
      if (! result.endsWith("/")) {
        result += "/";
      }

      return result;
    }

    isFile (type) {
      return type === "file";
    }

    isDirectory (type) {
      return ["dir", "directory"].indexOf(type) !== -1;
    }

    static executeFileDefault (path) {
      console.log(`Executed ${path}.`);
    }
    //######################### Data Utils End ############################

    //######################### IO Utils Begin ############################
    getDirInfo (path) {
      var complteUrl = this.props.storageUrl;
      complteUrl += encodeURIComponent(path).replace(/\%2F/g, "/");

      return this.props.getDirInfo(complteUrl);
    }

    static getDirInfoDefault (path) {
      var result = null;

      // get file lsit
      $.ajax({
        "url" : path,
        "type" : "GET",
        "cache" : false,
        "async" : false,
        "success" : function (data) {
          result = result || {};
          result.list = JSON.parse(data);
        },
        "fail" : function (e) {
          result = null;
          alert ("Exception occured.");
          console.error(e);
        }
      });

      return result;
    }
    //######################### IO Utils End ##############################
  }
  FileDialog.propTypes = {
    "rootPath" : React.PropTypes.string,
    "storageUrl" : React.PropTypes.string,
    "getDirInfo" : React.PropTypes.func,
    "executeFile" : React.PropTypes.func
  };
  FileDialog.defaultProps = {
    "rootPath" : "/",
    "storageUrl" : "http://localhost:8080",
    "getDirInfo" : FileDialog.getDirInfoDefault,
    "executeFile" : FileDialog.executeFileDefault
  };

  global.FileDialog = FileDialog;

})(window,
  window["$"] || window["JQuery"],
  window["React"],
  // React Bootstrap Table Component
  window["BootstrapTable"],
  window["TableHeaderColumn"]
);
