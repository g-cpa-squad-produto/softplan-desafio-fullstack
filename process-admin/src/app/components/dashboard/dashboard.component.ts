import { Component, OnInit } from "@angular/core";
import { Dashboard } from "../../model/dashboard.model";
import { ProcessService } from "../../services/process.service";
import { ResponseApi } from "../../model/response-api";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  dashboard: Dashboard = new Dashboard();
  public amountProcesses: number;
  message: {};
  classCss: {};

  constructor(private processService: ProcessService) {}

  ngOnInit() {
    this.processService.summary().subscribe(
      (responseApi: ResponseApi) => {
        console.log(responseApi["data"]["content"]);
        //this.dashboard = ;
        console.log(this.dashboard);
        this.amountProcesses = 0;
          /*this.dashboard.amountPending +
          this.dashboard.amountAnalyzing +
          this.dashboard.amountCanceled +
          this.dashboard.amountClosed;*/
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["error"]["errors"][0]
        });
      }
    );
  }

  showMessage(message: { type: string; text: string }): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      alert: true
    };
    this.classCss["alert-" + type] = true;
  }
}
