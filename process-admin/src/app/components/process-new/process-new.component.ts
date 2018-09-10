import { Component, OnInit, ViewChild } from "@angular/core";
import { Process } from "../../model/process.model";
import { SharedService } from "../../services/shared.service";
import { NgForm } from "@angular/forms";
import { ProcessService } from "../../services/process.service";
import { ActivatedRoute } from "@angular/router";
import { ResponseApi } from "../../model/response-api";
import { UserService } from "../../services/user.service";
import { User } from "../../model/user.model";

@Component({
  selector: "app-process-new",
  templateUrl: "./process-new.component.html",
  styleUrls: ["./process-new.component.css"]
})
export class ProcessNewComponent implements OnInit {
  @ViewChild("form")
  form: NgForm;

  process = new Process("", null, "", "", "", null, null, "", null);
  shared: SharedService;
  message: {};
  classCss: {};
  operation: string = ":: Novo";
  usersReviews: Array<User>;

  constructor(
    private processService: ProcessService,
    private route: ActivatedRoute,
    private userService: UserService
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    let id: string = this.route.snapshot.params["id"];
    this.userService.findAllReviews().subscribe(
      (responseApi: ResponseApi) => {
        this.usersReviews = responseApi.data;
        console.log(this.usersReviews);        
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["error"]["errors"][0]
        });
      }
    );

    if (id) {
      this.findById(id);
    }
  }

  findById(id: string) {
    this.processService.findById(id).subscribe(
      (responseApi: ResponseApi) => {
        this.process = responseApi.data;
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["error"]["errors"][0]
        });
      }
    );
  }

  register() {
    this.message = {};
    console.log(this.process);
    this.processService.createOrUpdate(this.process).subscribe(
      (responseApi: ResponseApi) => {
        this.process = new Process("", null, "", "", "", null, null, "", null);
        let processRet: Process = responseApi.data;
        this.form.resetForm();
        this.showMessage({
          type: "success",
          text: `Registrado ${processRet.number} com sucesso!`
        });
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

  getFromGroupClass(isInvalid: boolean, isDirty: boolean): {} {
    return {
      "form-group": true,
      "has-error": isInvalid && isDirty,
      "has-sucess": !isInvalid && isDirty
    };
  }
}
