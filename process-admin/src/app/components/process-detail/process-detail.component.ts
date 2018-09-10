import { Component, OnInit } from "@angular/core";
import { ProcessService } from "../../services/process.service";
import { ActivatedRoute } from "@angular/router";
import { SharedService } from "../../services/shared.service";
import { Process } from "../../model/process.model";
import { ResponseApi } from "../../model/response-api";
import { ProcessReview } from "../../model/process.review.model";

@Component({
  selector: "app-process-detail",
  templateUrl: "./process-detail.component.html",
  styleUrls: ["./process-detail.component.css"]
})
export class ProcessDetailComponent implements OnInit {
  process = new Process("", null, "", "", "", null, null, "", null);
  review: string = "";

  shared: SharedService;
  
  message: {};
  classCss: {};

  constructor(
    private processService: ProcessService,
    private route: ActivatedRoute
  ) {
    this.shared = SharedService.getInstance();
  }
  ngOnInit() {
    let id: string = this.route.snapshot.params["id"];
    if (id) {
      this.findById(id);
    }
  }

  findById(id: string) {
    this.processService.findById(id).subscribe(
      (responseApi: ResponseApi) => {
        this.process = responseApi.data;
        this.process.date = new Date(this.process.date).toISOString();
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["error"]["errors"][0]
        });
      }
    );
  }

  sendReview(description: string): void {
    console.log(this.review);
    description = this.review;
    this.processService.sendReview(this.process, description).subscribe(
      (responseApi: ResponseApi) => {
        this.process = responseApi.data;
        this.process.date = new Date(this.process.date).toISOString();
        this.showMessage({
          type: "success",
          text: "Análise enviada com sucesso!"
        });
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["errors"]["error"][0]
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

  getFromGroupClass(isInvalid: boolean, isDirty: boolean): {} {
    return {
      "form-group": true,
      "has-error": isInvalid && isDirty,
      "has-sucess": !isInvalid && isDirty
    };
  }
}
